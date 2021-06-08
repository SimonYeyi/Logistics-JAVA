package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.LogisticsException;

public class OrderGoodsEmptyException extends LogisticsException {

    public OrderGoodsEmptyException() {
        super("Order goods is empty");
    }
}
