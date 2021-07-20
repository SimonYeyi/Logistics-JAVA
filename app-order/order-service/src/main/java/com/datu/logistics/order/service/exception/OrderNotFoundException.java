package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderNotFoundException extends LogisticsException {

    private OrderNotFoundException(String message) {
        this(message, null);
    }

    protected OrderNotFoundException(String message, Throwable e) {
        super(null, message, e);
    }

    public static OrderNotFoundException orderNo(String orderNo) {
        return new OrderNotFoundException("Order not found: orderId is " + orderNo);
    }
}
