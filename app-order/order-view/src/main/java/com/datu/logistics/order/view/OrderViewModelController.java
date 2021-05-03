package com.datu.logistics.order.view;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.view.vo.OrderVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@RestController
public class OrderViewModelController {

    @Resource
    private OrderApplicationService orderApplicationService;

    @GetMapping("view-model")
    public OrderVO getOrderViewModel(@RequestParam("orderNo") String orderNo) {
        OrderDTO order = orderApplicationService.searchOrder(orderNo);
        return new OrderVO(order);
    }
}
