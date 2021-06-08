package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderNotFoundException extends LogisticsException {

    public OrderNotFoundException(String message) {
        this(null, message, null);
    }

    protected OrderNotFoundException(String code, String message, Throwable e) {
        super(code, message, e);
    }

    public static OrderNotFoundException orderNo(String orderNo) {
        return new OrderNotFoundException("Order not found: orderId is " + orderNo);
    }
}
