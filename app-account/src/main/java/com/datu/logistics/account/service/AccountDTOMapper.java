package com.datu.logistics.account.service;

import com.datu.logistics.account.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountDTOMapper {
    AccountDTOMapper INSTANCE = Mappers.getMapper(AccountDTOMapper.class);

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.name", target = "accountName")
    @Mapping(expression = "java(tokens[0])", target = "token")
    @Mapping(expression = "java(tokens[1])", target = "refreshToken")
    AccountDTO toDTO(Account account, String[] tokens);
}
