package com.datu.logistics.account.domain.exception;

import com.datu.logistics.exception.LogisticsException;

public class AccountPasswordError extends LogisticsException {
    public AccountPasswordError(String message) {
        this(message, null);
    }

    protected AccountPasswordError(String message, Throwable e) {
        super(null, message, e);
    }

    public static AccountPasswordError value(String accountName) {
        return new AccountPasswordError("Account name or password error: " + accountName);
    }
}
