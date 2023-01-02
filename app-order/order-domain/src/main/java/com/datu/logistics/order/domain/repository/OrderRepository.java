package com.datu.logistics.order.domain.repository;

import com.datu.logistics.order.domain.model.Order;

import java.math.BigInteger;
import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    Order of(String no);

    Order of(long id);

    List<Order> pageOf(int page, int pageSize);

    List<Order> firstPage();
}
