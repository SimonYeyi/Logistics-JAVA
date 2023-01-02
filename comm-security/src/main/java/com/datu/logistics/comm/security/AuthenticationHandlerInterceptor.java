package com.datu.logistics.comm.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String client = request.getHeader("Client");
        if ("openfeign".equals(client)) return true;
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) return true;
        if (!(handler instanceof HandlerMethod)) return true;
        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(SkipAuthentication.class)) return true;
        if (method.getDeclaringClass().isAnnotationPresent(SkipAuthentication.class)) return true;
        String token = request.getHeader("Authentication");
        try {
            JwtUtils.verifyToken(token);
        } catch (JWTVerificationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw e;
        }
        return true;
    }
}
