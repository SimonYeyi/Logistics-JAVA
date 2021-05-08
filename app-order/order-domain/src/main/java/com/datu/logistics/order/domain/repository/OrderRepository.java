package com.datu.logistics.order.domain.repository;

import com.datu.logistics.order.domain.model.Order;

import java.math.BigInteger;
import java.util.List;

public interface OrderRepository {
    Order orderOf(String no);

    Order save(Order order);

    List<Order> pageOf(int page, int pageSize);
}
