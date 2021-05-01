package com.datu.logistics.order.service;

import com.datu.logistics.order.service.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@FeignClient(value = "order", path = "order", primary = false)
public interface OrderApplicationService {

    @GetMapping("{id}/get")
    OrderDTO searchOrder(@PathVariable("id") Long id);

    @PostMapping("create")
    OrderDTO createOrder(OrderDTO orderDTO);
}
