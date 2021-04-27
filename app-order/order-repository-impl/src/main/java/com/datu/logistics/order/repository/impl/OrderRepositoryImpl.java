package com.datu.logistics.order.repository.impl;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Order orderOf(BigInteger id) {
        return new Order(id);
    }
}
