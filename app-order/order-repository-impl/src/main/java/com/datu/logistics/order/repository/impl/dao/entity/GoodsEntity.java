package com.datu.logistics.order.repository.impl.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods", schema = "logistics_order")
public class GoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int weight;
    private int volume;
    private int amount;
    private String orderNo;
}
