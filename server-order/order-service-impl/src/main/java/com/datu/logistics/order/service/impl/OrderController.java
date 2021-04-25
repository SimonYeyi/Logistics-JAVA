package com.datu.logistics.order.service.impl;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController()
public class OrderController implements OrderApplicationService {

    @Override
    public OrderDTO order(BigInteger orderId) {
        return new OrderDTO(orderId);
    }
}
