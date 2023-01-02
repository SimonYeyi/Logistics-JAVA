package com.datu.logistics.order.view;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.view.mapper.OrderVOMapper;
import com.datu.logistics.order.view.vo.OrderVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("view-model")
public class OrderViewModelController {

    @Resource
    private OrderApplicationService orderApplicationService;

    @GetMapping("order/get")
    public OrderVO getOrder(@RequestParam("orderNo") String orderNo) {
        OrderDTO order = orderApplicationService.getOrder(orderNo);
        return OrderVOMapper.INSTANCE.toVO(order);
    }
}
