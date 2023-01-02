package com.datu.logistics.account.domain.exception;

import com.datu.logistics.exception.ServiceException;

public class AccountPasswordError extends ServiceException {

    protected AccountPasswordError(String message) {
        super(message);
    }

    public static AccountPasswordError value(String accountName) {
        return new AccountPasswordError("Account name or password error: " + accountName);
    }
}
