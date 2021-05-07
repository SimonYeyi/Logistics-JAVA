package com.datu.logistics.order.repository.impl.dao;

import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByNo(String no);
}
