package com.datu.logistics.order.repository.impl;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.repository.impl.dao.OrderDAO;
import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import com.datu.logistics.order.repository.impl.mapper.OrderEntityMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderDAO.saveAndFlush(OrderEntityMapper.INSTANCE.toEntity(order));
        return OrderEntityMapper.INSTANCE.toModel(orderEntity);
    }

    @Override
    public Order of(String no) {
        Optional<OrderEntity> orderEntity = orderDAO.findByNo(no);
        return orderEntity.map(OrderEntityMapper.INSTANCE::toModel).orElse(null);
    }

    @Override
    public Order of(long id) {
        Optional<OrderEntity> orderEntity = orderDAO.findById(id);
        return orderEntity.map(OrderEntityMapper.INSTANCE::toModel).orElse(null);
    }

    @Override
    public List<Order> pageOf(int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        return orderDAO.findAll(pageable)
                .map(OrderEntityMapper.INSTANCE::toModel)
                .getContent();
    }

    @Override
    public List<Order> firstPage() {
        return pageOf(1, 20);
    }
}
