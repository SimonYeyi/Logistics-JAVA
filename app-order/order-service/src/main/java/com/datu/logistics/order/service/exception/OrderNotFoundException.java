package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.ServiceException;

public class OrderNotFoundException extends ServiceException {

    protected OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException orderNo(String orderNo) {
        return new OrderNotFoundException("Order not found: orderNo is " + orderNo);
    }
}
