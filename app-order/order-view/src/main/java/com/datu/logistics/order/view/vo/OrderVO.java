package com.datu.logistics.order.view.vo;

import com.datu.logistics.order.service.dto.OrderDTO;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class OrderVO {
    private long orderId;
    private String orderNo;
    private String transferOrderNo;
    private Date orderTime;
    private String destination;

    public OrderVO(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
        this.orderNo = orderDTO.getOrderNo();
        this.transferOrderNo = orderDTO.getTransferOrderNo();
        this.orderTime = orderDTO.getOrderTime();
        this.destination = orderDTO.getDestination();
    }
}
