package com.datu.logistics.order.service.impl;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@RestController
public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public OrderDTO searchOrder(BigInteger orderId) {
        Order order = orderRepository.orderOf(orderId);
        return new OrderDTO(order.getId());
    }
}
