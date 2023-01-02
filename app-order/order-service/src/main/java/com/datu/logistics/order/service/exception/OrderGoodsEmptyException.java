package com.datu.logistics.order.service.exception;

import com.datu.logistics.exception.ServiceException;

public class OrderGoodsEmptyException extends ServiceException {

    protected OrderGoodsEmptyException(String message) {
        super("Order goods is empty");
    }
}