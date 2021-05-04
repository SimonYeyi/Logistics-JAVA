package com.datu.logistics.order.domain.repository;

import com.datu.logistics.order.domain.model.Order;

import java.math.BigInteger;

public interface OrderRepository {
    Order orderOf(String id);

    Order save(Order order);
}
