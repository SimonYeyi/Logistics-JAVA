package com.datu.logistics.order.view.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderVO {
    private String orderNo;
    private String delegateOrderNo;
    private Date orderTime;
    private String destination;
}
