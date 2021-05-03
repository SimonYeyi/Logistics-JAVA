package com.datu.logistics.order.view.vo;

import com.datu.logistics.order.service.dto.OrderDTO;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class OrderVO {
    private String orderNo;
    private String delegateOrderNo;
    private Date orderTime;
    private String destination;

    public OrderVO(OrderDTO orderDTO) {
        this.orderNo = orderDTO.getNo();
        this.delegateOrderNo = orderDTO.getDelegateOrders().stream()
                .findFirst().orElse(null).getNo();
        this.orderTime = orderDTO.getTime();
        this.destination = orderDTO.getTo().getAddress();
    }
}
