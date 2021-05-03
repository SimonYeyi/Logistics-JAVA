package com.datu.logistics.order.repository.impl.dao.entity;

import com.datu.logistics.order.domain.model.DelegateOrder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "order_", schema = "logistics_order")
public class OrderEntity {
    @Id
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

    @OneToMany(mappedBy = "id")
    private Set<GoodsEntity> goodsEntities;
    @OneToMany(mappedBy = "no")
    private Set<DelegateOrderEntity> delegateOrders;
}
