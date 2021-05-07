package com.datu.logistics.order.repository.impl.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "order_", schema = "logistics_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private int amount;
    private int amountPaid;
    private String fromFullName;
    private String fromPhone;
    private String fromAddress;
    private String toFullName;
    private String toPhone;
    private String toAddress;
    private Date time;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Set<GoodsEntity> goodsEntities = new HashSet<>();
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Set<DelegateOrderEntity> delegateOrders = new HashSet<>();
}
