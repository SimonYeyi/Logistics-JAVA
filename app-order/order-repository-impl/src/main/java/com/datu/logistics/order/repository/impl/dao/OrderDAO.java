package com.datu.logistics.order.repository.impl.dao;

import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, String> {
}
