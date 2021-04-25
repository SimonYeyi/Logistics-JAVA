package com.datu.logistics.order.service;

import com.datu.logistics.order.service.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(value = "order", path = "order")
public interface OrderApplicationService {

    @GetMapping("get")
    OrderDTO order(@RequestParam("orderId") BigInteger orderId);
}
