package com.datu.logistics.order.service.command;

import lombok.Data;

import java.util.Date;

@Data
public final class OrderModifyCommand {
    private long orderId;
    private String orderNo;
    private Date orderTime;
    private Float goodsWeight;
    private Integer goodsQuantity;
    private String incomingChannel;
    private String comment;
    private String toAddress;
    private String delegateOrderNo;
}
