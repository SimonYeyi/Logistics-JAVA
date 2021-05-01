package com.datu.logistics.order.service.impl;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.service.exception.OrderNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@Primary
@RestController
public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public OrderDTO searchOrder(Long orderId) {
        Order order = orderRepository.orderOf(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = Order.create(
                orderDTO.getOrderNo(),
                orderDTO.getOrderTime(),
                orderDTO.getDestination()
        );
        if (orderDTO.getTransferOrderNo() != null) {
            order.transport(orderDTO.getTransferOrderNo());
        }
        orderRepository.save(order);
        return toOrderDTO(order);
    }

    private OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setOrderNo(order.getNo());
        orderDTO.setTransferOrderNo(order.getTransferNo());
        orderDTO.setOrderTime(order.getTime());
        orderDTO.setDestination(order.getDestination());
        return orderDTO;
    }
}
