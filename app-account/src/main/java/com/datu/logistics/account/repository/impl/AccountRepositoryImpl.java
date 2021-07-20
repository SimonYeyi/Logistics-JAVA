package com.datu.logistics.account.repository.impl;

import com.datu.logistics.account.domain.model.Account;
import com.datu.logistics.account.domain.repository.AccountRepository;
import com.datu.logistics.account.repository.impl.dao.AccountDAO;
import com.datu.logistics.account.repository.impl.dao.entity.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountDAO accountDAO;

    public AccountRepositoryImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        accountDAO.save(entity);
        return toModel(entity);
    }

    @Override
    public Account accountOf(String name) {
        AccountEntity entity = accountDAO.findByName(name);
        return toModel(entity);
    }

    private static Account toModel(AccountEntity entity) {
        if (entity == null) return null;
        return new Account(entity.getId(), entity.getName(), entity.getPassword());
    }

    private static AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setName(account.getName());
        entity.setPassword(account.getPassword());
        return entity;
    }
}
