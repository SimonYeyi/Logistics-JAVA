package com.datu.logistics.account.domain.repository;

import com.datu.logistics.account.domain.model.Account;

public interface AccountRepository {

    Account save(Account account);

    Account of(String name);
}
