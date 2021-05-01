package com.datu.logistics.order.service.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order not found: orderId is " + orderId);
    }
}
