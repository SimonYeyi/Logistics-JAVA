package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderGoodsEmptyException extends LogisticsException {

    public OrderGoodsEmptyException() {
        this(null, "Order goods is empty", null);
    }

    protected OrderGoodsEmptyException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
