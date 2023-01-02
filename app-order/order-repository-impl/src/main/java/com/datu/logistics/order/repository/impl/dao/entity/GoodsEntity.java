package com.datu.logistics.order.repository.impl.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = "orderEntity")
@EqualsAndHashCode(exclude = {"orderEntity"})
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

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
