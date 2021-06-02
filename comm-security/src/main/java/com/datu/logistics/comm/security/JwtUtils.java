package com.datu.logistics.comm.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "123456"; //秘钥
    private static final long TOKEN_EXPIRE_TIME = 60 * 5; //token过期时间
    private static final long REFRESH_TOKEN_EXPIRE_TIME = TOKEN_EXPIRE_TIME * 20; //refreshToken过期时间
    private static final String ISSUER = "JwtUtils"; //签发人
    private static StringRedisTemplate stringRedisTemplate;
    private static final String KEY_CLAIM_NAME = "CLAIM_NAME";
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_USER_NAME = "USER_NAME";
    private static final String KEY_TOKEN_VERSION = "TOKEN_VERSION";
    private static final String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";

    private JwtUtils(StringRedisTemplate stringRedisTemplate) {
        JwtUtils.stringRedisTemplate = stringRedisTemplate;
    }

    public static String[] generateToken(Object userId, String username, @Nullable Map<String, Object> claim) {
        String token = onlyGenerateToken(userId, username, claim);
        String refreshToken = generateRefreshToken(userId);
        return new String[]{token, refreshToken};
    }

    private static String onlyGenerateToken(Object userId, String username, @Nullable Map<String, Object> claim) {
        if (claim == null) claim = new HashMap<>(2);
        int tokenVersion = setTokenVersion(userId);
        claim.put(KEY_USER_ID, userId);
        claim.put(KEY_USER_NAME, username);
        claim.put(KEY_TOKEN_VERSION, tokenVersion);
        for (Map.Entry<String, Object> entry : claim.entrySet()) {
            if (entry.getValue() instanceof Long) {
                entry.setValue(entry.getValue() + "_L");
            }
        }
        final Date now = new Date();
        final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
        return JWT.create()
                .withIssuer(ISSUER) //签发人
                .withIssuedAt(now) //签发时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME * 60 * 1000)) //过期时间
                .withClaim(KEY_CLAIM_NAME, claim)
                .sign(algorithm);
    }

    private static int setTokenVersion(Object userId) {
        int currentTokenVersion = getCurrentTokenVersion(userId);
        int newTokenVersion = ++currentTokenVersion;
        stringRedisTemplate.opsForHash().put(userTokenKey(userId), KEY_TOKEN_VERSION, newTokenVersion + "");
        return newTokenVersion;
    }

    private static int getCurrentTokenVersion(Object userId) {
        String lastTokenVersion = (String) stringRedisTemplate.opsForHash().get(userTokenKey(userId), KEY_TOKEN_VERSION);
        return lastTokenVersion == null ? 0 : Integer.parseInt(lastTokenVersion);
    }

    private static <T> String generateRefreshToken(T userId) {
        String userTokenKey = userTokenKey(userId);
        String refreshToken = userId.toString() + UUID.randomUUID();
        stringRedisTemplate.opsForHash().put(userTokenKey, KEY_REFRESH_TOKEN, refreshToken);
        stringRedisTemplate.expire(userTokenKey, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
        return refreshToken;
    }

    public static String refreshToken(String token, String refreshToken) throws JWTVerificationException {
        Object userId = getUserId(token);
        String storedRefreshToken = (String) stringRedisTemplate.opsForHash().get(userTokenKey(userId), KEY_REFRESH_TOKEN);
        if (storedRefreshToken == null) {
            throw new TokenExpiredException("refreshToken expire");
        }
        if (!storedRefreshToken.equals(refreshToken)) {
            throw new JWTVerificationException("refreshToken error");
        }
        return onlyGenerateToken(userId, getUsername(token), getClaim(token));
    }

    private static String userTokenKey(Object userId) {
        return "TOKEN_FOR_USER_" + userId.toString();
    }

    public static void verifyToken(String token) throws JWTVerificationException {
        Object userId = getUserId(token);
        int tokenVersion = getClaim(token, KEY_TOKEN_VERSION);
        int currentTokenVersion = getCurrentTokenVersion(userId);
        if (currentTokenVersion == 0 || tokenVersion < currentTokenVersion) {
            throw new JWTVerificationException("token version expire");
        }
        final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); //算法
        JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token);
    }

    public static <T> T getClaim(String token, String name) {
        Object claim = getClaim(token).get(name);
        String claimString = claim + "";
        if (claimString.endsWith("_L")) {
            claim = Long.parseLong(claimString.substring(0, claimString.length() - 2));
        }
        return (T) claim;
    }

    public static Map<String, Object> getClaim(String token) {
        return JWT.decode(token)
                .getClaim(KEY_CLAIM_NAME)
                .asMap();
    }

    public static <T> T getUserId(String token) {
        return getClaim(token, KEY_USER_ID);
    }

    public static String getUsername(String token) {
        return getClaim(token, KEY_USER_NAME);
    }
}
