package com.datu.logistics.order.view.vo;

import com.datu.logistics.order.service.dto.OrderDTO;
import lombok.Data;

import java.math.BigInteger;

@Data
public class OrderVo {
    private final BigInteger orderId;

    public OrderVo(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
    }
}
