package com.datu.logistics.account.domain.exception;

import com.datu.logistics.exception.ServiceException;

public class AccountNameNotFound extends ServiceException {

    protected AccountNameNotFound(String message) {
        super(message);
    }

    public static AccountNameNotFound value(String accountName) {
        return new AccountNameNotFound("Account not found: " + accountName);
    }
}
