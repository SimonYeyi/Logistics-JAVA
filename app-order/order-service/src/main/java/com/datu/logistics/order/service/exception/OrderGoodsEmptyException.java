package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderGoodsEmptyException extends LogisticsException {

    public OrderGoodsEmptyException() {
        this("Order goods is empty", null);
    }

    protected OrderGoodsEmptyException(String message, Throwable e) {
        super(null, message, e);
    }
}
