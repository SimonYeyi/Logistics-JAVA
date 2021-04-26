package com.datu.logistics.order.service;

import com.datu.logistics.order.service.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@FeignClient(value = "order", path = "order")
public interface OrderApplicationService {

    @GetMapping("{id}/get")
    OrderDTO orderOf(@PathVariable("id") BigInteger id);
}
