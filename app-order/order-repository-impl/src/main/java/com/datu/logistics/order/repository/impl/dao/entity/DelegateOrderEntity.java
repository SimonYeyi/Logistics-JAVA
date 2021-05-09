package com.datu.logistics.order.repository.impl.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Data
@EqualsAndHashCode(exclude = {"orderEntity"})
@Entity
@Table(name = "delegate_order", schema = "logistics_order")
public class DelegateOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String no;
    private String corporateName;
    private int amount;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id", nullable = false)
    private GoodsEntity goodsEntity;
}
