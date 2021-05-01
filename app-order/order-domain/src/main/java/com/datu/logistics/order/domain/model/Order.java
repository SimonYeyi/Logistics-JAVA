package com.datu.logistics.order.domain.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private final String no;
    private String transferNo;
    private final Date time;
    private final String destination;

    public static Order create(String orderNo, Date orderTime, String destination) {
        return new Order(orderNo, orderTime, destination);
    }

    public void saved(long orderId) {
        this.id = orderId;
    }

    public void transport(String transferOrderNo) {
        this.transferNo = transferOrderNo;
    }
}
