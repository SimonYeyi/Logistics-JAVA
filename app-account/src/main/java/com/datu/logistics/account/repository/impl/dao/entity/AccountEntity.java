package com.datu.logistics.account.repository.impl.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "account")
@Table(name = "account", schema = "logistics_account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
}