package com.datu.logistics.order.repository.impl;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.repository.impl.dao.OrderDAO;
import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order orderOf(long id) {
        Optional<OrderEntity> orderEntity = orderDAO.findById(id);
        return orderEntity.map(OrderEntity::toOrder).orElse(null);
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderDAO.saveAndFlush(OrderEntity.newInstance(order));
        order.saved(orderEntity.getId());
        return order;
    }
}
