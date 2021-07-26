package com.datu.logistics.account.service;

import com.datu.logistics.account.domain.exception.AccountNameNotFound;
import com.datu.logistics.account.domain.model.Account;
import com.datu.logistics.account.domain.repository.AccountRepository;
import com.datu.logistics.comm.security.JwtUtils;
import com.datu.logistics.comm.security.SkipAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@SkipAuthentication
@Api(tags = "账户接口")
@RestController
public class AccountApplicationService {
    private final AccountRepository accountRepository;

    public AccountApplicationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @ApiOperation("账户注册")
    //@PostMapping("register")
    public AccountDTO register(@RequestBody AccountRegisterCommand accountRegisterCommand) {
        Account account = Account.register(accountRegisterCommand.getAccountName(), accountRegisterCommand.getPassword());
        account = accountRepository.save(account);
        String[] tokens = JwtUtils.generateToken(account.getId(), account.getName(), null);
        return toDTO(account, tokens);
    }

    @ApiOperation("账户登录")
    @PostMapping("login")
    public AccountDTO login(@RequestBody AccountLoginCommand accountLoginCommand) {
        Account account = accountRepository.of(accountLoginCommand.getAccountName());
        if (account == null) {
            throw AccountNameNotFound.value(accountLoginCommand.getAccountName());
        }
        account.login(accountLoginCommand.getPassword());
        String[] tokens = JwtUtils.generateToken(account.getId(), account.getName(), null);
        return toDTO(account, tokens);
    }

    @ApiOperation("令牌刷新")
    @PostMapping("token/refresh")
    public AccountDTO refreshToken(@RequestHeader("Authentication") String token, @RequestParam("refreshToken") String refreshToken) {
        String newToken = JwtUtils.refreshToken(token, refreshToken);
        long accountId = JwtUtils.getUserId(newToken);
        String accountName = JwtUtils.getUsername(newToken);
        return new AccountDTO(accountId, accountName, newToken, refreshToken);
    }

    private static AccountDTO toDTO(Account account, String[] tokens) {
        return new AccountDTO(account.getId(), account.getName(), tokens[0], tokens[1]);
    }
}
