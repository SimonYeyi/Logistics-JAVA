package com.datu.logistics.account.repository.impl;

import com.datu.logistics.account.domain.model.Account;
import com.datu.logistics.account.domain.repository.AccountRepository;
import com.datu.logistics.account.repository.impl.dao.AccountDAO;
import com.datu.logistics.account.repository.impl.dao.entity.AccountEntity;
import com.datu.logistics.account.repository.impl.mapper.AccountEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountDAO accountDAO;

    public AccountRepositoryImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = AccountEntityMapper.INSTANCE.toEntity(account);
        accountDAO.save(entity);
        return AccountEntityMapper.INSTANCE.toModel(entity);
    }

    @Override
    public Account of(String name) {
        AccountEntity entity = accountDAO.findByName(name);
        return AccountEntityMapper.INSTANCE.toModel(entity);
    }
}
