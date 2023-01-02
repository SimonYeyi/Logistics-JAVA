package com.datu.logistics.account.domain.model;

import com.datu.logistics.account.domain.exception.AccountPasswordError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Account {
    private final Long id;
    private final String name;
    private final String password;

    public static Account register(String accountName, String password) {
        //密码加密
        return new Account(null, accountName, password);
    }

    public void login(String password) {
        if (!this.password.equals(password)) {
            throw AccountPasswordError.value(name);
        }
    }
}
