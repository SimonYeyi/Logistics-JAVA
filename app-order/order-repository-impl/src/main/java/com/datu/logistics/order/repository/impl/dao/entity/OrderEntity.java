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
    private Float goodsWeight;
    private Integer goodsQuantity;
    private String incomingChannel;
    private String comment;
    private String fromFullName;
    private String fromPhone;
    private String fromAddress;
    private String toFullName;
    private String toPhone;
    private String toAddress;
    private Date time;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private Set<GoodsEntity> goodsEntities = new HashSet<>();
    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private Set<DelegateOrderEntity> delegateOrders = new HashSet<>();

    public void setGoodsEntities(Set<GoodsEntity> goodsEntities) {
        for (GoodsEntity goodsEntity : goodsEntities) {
            goodsEntity.setOrderEntity(this);
        }
        this.goodsEntities = goodsEntities;
    }

    public void setDelegateOrders(Set<DelegateOrderEntity> delegateOrders) {
        for (DelegateOrderEntity delegateOrder : delegateOrders) {
            delegateOrder.setOrderEntity(this);
        }
        this.delegateOrders = delegateOrders;
    }
}
