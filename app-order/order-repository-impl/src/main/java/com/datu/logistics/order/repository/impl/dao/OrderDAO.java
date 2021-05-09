package com.datu.logistics.order.repository.impl.dao;

import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OrderDAO extends PagingAndSortingRepository<OrderEntity, Long>, JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByNo(String no);
}
