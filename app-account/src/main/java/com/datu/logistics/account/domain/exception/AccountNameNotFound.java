package com.datu.logistics.account.domain.exception;

import com.datu.logistics.exception.LogisticsException;

public class AccountNameNotFound extends LogisticsException {

    public AccountNameNotFound(String message) {
        this(message, null);
    }

    protected AccountNameNotFound(String message, Throwable e) {
        super(null, message, e);
    }

    public static AccountNameNotFound value(String accountName) {
        return new AccountNameNotFound("Account not found: " + accountName);
    }
}
