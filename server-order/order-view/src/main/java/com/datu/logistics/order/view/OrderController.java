package com.datu.logistics.order.view;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.view.vo.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@RestController
@RequestMapping("view-model")
public class OrderController {

    @Resource
    private OrderApplicationService orderApplicationService;

    @GetMapping("get")
    public OrderVo getOrder(@RequestParam("orderId") BigInteger orderId) {
        OrderDTO order = orderApplicationService.orderOf(orderId);
        return new OrderVo(order);
    }
}
