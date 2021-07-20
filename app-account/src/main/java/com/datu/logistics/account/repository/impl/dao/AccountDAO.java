package com.datu.logistics.account.repository.impl.dao;

import com.datu.logistics.account.repository.impl.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByName(String name);
}
