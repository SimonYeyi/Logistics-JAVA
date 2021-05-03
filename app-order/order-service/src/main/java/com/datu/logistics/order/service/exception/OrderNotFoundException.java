package com.datu.logistics.order.service.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String orderNo) {
        super("Order not found: orderId is " + orderNo);
    }
}
