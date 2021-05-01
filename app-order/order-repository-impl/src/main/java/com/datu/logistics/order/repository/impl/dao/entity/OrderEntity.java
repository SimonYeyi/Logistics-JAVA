package com.datu.logistics.order.repository.impl.dao.entity;

import com.datu.logistics.order.domain.model.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "order0")
public class OrderEntity {
    @Id
    private Long id;
    private String no;
    private String transferNo;
    private Date time;
    private String destination;

    public static OrderEntity newInstance(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setNo(order.getNo());
        orderEntity.setTransferNo(order.getTransferNo());
        orderEntity.setTime(order.getTime());
        orderEntity.setDestination(order.getDestination());
        return orderEntity;
    }

    public Order toOrder() {
        return new Order(
                id,
                no,
                transferNo,
                time,
                destination
        );
    }
}
