package com.datu.logistics.account.repository.impl.mapper;

import com.datu.logistics.account.domain.model.Account;
import com.datu.logistics.account.repository.impl.dao.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountEntityMapper {
    AccountEntityMapper INSTANCE = Mappers.getMapper(AccountEntityMapper.class);

    AccountEntity toEntity(Account model);

    Account toModel(AccountEntity entity);
}
