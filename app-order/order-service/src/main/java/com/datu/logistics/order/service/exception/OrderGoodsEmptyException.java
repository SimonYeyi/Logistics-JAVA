package com.datu.logistics.order.service.exception;

public class OrderGoodsEmptyException extends RuntimeException {

    public OrderGoodsEmptyException() {
        super("Order goods is empty");
    }
}
