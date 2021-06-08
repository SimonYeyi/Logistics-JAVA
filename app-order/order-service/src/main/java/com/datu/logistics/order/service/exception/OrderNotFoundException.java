package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderNotFoundException extends LogisticsException {

    public OrderNotFoundException(String orderNo) {
        super("Order not found: orderId is " + orderNo);
    }
}
