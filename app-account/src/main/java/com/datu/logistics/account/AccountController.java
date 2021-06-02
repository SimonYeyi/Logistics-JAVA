package com.datu.logistics.account;

import com.datu.logistics.comm.security.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "账户接口")
@RestController
public class AccountController {
    private static final List<AccountRegisterCommand> accounts = new ArrayList<>();

    @ApiOperation("账户注册")
    @PostMapping("register")
    public AccountDTO register(@RequestBody AccountRegisterCommand accountRegisterCommand) {
        accounts.add(accountRegisterCommand);
        AccountLoginCommand accountLoginCommand = new AccountLoginCommand();
        accountLoginCommand.setAccountName(accountRegisterCommand.getAccountName());
        accountLoginCommand.setPassword(accountRegisterCommand.getPassword());
        return login(accountLoginCommand);
    }

    @ApiOperation("账户登录")
    @PostMapping("login")
    public AccountDTO login(@RequestBody AccountLoginCommand accountLoginCommand) {
        String accountName = accountLoginCommand.getAccountName();
        long accountId = 0;
        for (int i = 0; i < accounts.size(); i++) {
            AccountRegisterCommand it = accounts.get(i);
            boolean ok = it.getAccountName().equals(accountName)
                    && it.getPassword().equals(accountLoginCommand.getPassword());
            if (ok) {
                accountId = i + 1;
                break;
            }
        }
        if (accountId == 0) {
            throw new RuntimeException("accountName or password error");
        }
        String[] tokens = JwtUtils.generateToken(accountId, accountName, null);
        return new AccountDTO(accountId, accountName, tokens[0], tokens[1]);
    }

    @ApiOperation("令牌刷新")
    @PostMapping("token/refresh")
    public AccountDTO refreshToken(@RequestHeader("Authentication") String token, @RequestParam("refreshToken") String refreshToken) {
        String newToken = JwtUtils.refreshToken(token, refreshToken);
        long accountId = JwtUtils.getUserId(newToken);
        String accountName = JwtUtils.getUsername(newToken);
        return new AccountDTO(accountId, accountName, newToken, refreshToken);
    }

    @ApiOperation("令牌测试")
    @GetMapping("get")
    public String testVerifyToken(@RequestHeader("Authentication") String token) {
        JwtUtils.verifyToken(token);
        return token;
    }
}
