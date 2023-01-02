package com.datu.logistics.order.service;

import com.datu.logistics.order.service.command.OrderAddCommand;
import com.datu.logistics.order.service.command.OrderDelegatedCommand;
import com.datu.logistics.order.service.command.OrderModifyCommand;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.service.dto.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "order", path = "order", primary = false)
public interface OrderApplicationService {

    @PostMapping("add")
    OrderDTO addOrder(@RequestBody OrderAddCommand orderAddCommand);

    @PostMapping("modify")
    OrderDTO modifyOrder(@RequestBody OrderModifyCommand orderModifyCommand);

    @GetMapping("{orderNo}/search")
    OrderDTO searchOrder(@PathVariable("orderNo") String orderNo);

    @GetMapping("list/search")
    List<OrderDTO> searchOrders(@RequestParam("orderNos") List<String> orderNos);

    @PatchMapping("{orderNo}/settle")
    OrderDTO settleOrder(@PathVariable("orderNo") String orderNo);

    @PatchMapping("{orderNo}/delegated")
    OrderDTO delegatedOrder(@PathVariable("orderNo") String orderNo,
                            @RequestBody OrderDelegatedCommand orderDelegatedCommand);

    @GetMapping("{orderNo}/get")
    OrderDTO getOrder(@PathVariable("orderNo") String orderNo);

    @GetMapping("page/get")
    PageDTO<OrderDTO> getOrderPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize);

    @GetMapping("page/first/get")
    PageDTO<OrderDTO> getFirstOrderPage();
}

