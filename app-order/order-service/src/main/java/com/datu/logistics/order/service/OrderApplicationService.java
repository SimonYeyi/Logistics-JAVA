package com.datu.logistics.order.service;

import com.datu.logistics.order.service.command.OrderCreateCommand;
import com.datu.logistics.order.service.command.OrderDelegatedCommand;
import com.datu.logistics.order.service.dto.DelegateOrderDTO;
import com.datu.logistics.order.service.dto.OrderDTO;
import feign.Body;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "order", path = "order", primary = false)
public interface OrderApplicationService {

    @PostMapping("create")
    OrderDTO createOrder(@RequestBody OrderCreateCommand orderCreateCommand);

    @GetMapping("{orderNo}/get")
    OrderDTO searchOrder(@PathVariable("orderNo") String orderNo);

    @GetMapping("list/get")
    List<OrderDTO> searchOrders(@RequestParam("orderNos") List<String> orderNos);

    @PatchMapping("{orderNo}/settle")
    OrderDTO settleOrder(@PathVariable("orderNo") String orderNo);

    @PatchMapping("{orderNo}/delegated")
    OrderDTO delegatedOrder(@PathVariable("orderNo") String orderNo,
                            @RequestParam("orderDelegatedCommand") OrderDelegatedCommand orderDelegatedCommand);
}
