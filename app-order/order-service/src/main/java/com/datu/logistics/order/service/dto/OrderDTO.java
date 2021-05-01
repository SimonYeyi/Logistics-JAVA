package com.datu.logistics.order.service.dto;

import lombok.*;
import org.springframework.core.annotation.Order;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private long orderId;
    private String orderNo;
    private String transferOrderNo;
    private Date orderTime;
    private String destination;
}