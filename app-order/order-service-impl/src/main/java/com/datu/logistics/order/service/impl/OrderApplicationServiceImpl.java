package com.datu.logistics.order.service.impl;

import com.datu.logistics.feign.RestService;
import com.datu.logistics.order.domain.model.DelegateOrder;
import com.datu.logistics.order.domain.model.Goods;
import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.command.OrderAddCommand;
import com.datu.logistics.order.service.command.OrderDelegatedCommand;
import com.datu.logistics.order.service.command.OrderModifyCommand;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.service.dto.PageDTO;
import com.datu.logistics.order.service.exception.OrderNotFoundException;
import com.datu.logistics.order.service.impl.mapper.OrderDTOMapper;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@RestService
public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Resource
    private final OrderRepository orderRepository;

    public OrderApplicationServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public OrderDTO addOrder(OrderAddCommand orderAddCommand) {
        Order order = Order.add(
                orderAddCommand.getOrderNo(),
                orderAddCommand.getOrderAmountPaid(),
                orderAddCommand.getOrderTime(),
                orderAddCommand.getGoodsWeight(),
                orderAddCommand.getGoodsQuantity(),
                orderAddCommand.getIncomingChannel(),
                orderAddCommand.getComment(),
                OrderDTOMapper.INSTANCE.toContacts(orderAddCommand.getFrom()),
                OrderDTOMapper.INSTANCE.toContacts(orderAddCommand.getTo()),
                orderAddCommand.getGoodsList().stream()
                        .map(OrderDTOMapper.INSTANCE::toGoods)
                        .collect(Collectors.toList())
        );
        order = orderRepository.save(order);
        if (orderAddCommand.getOrderDelegatedCommand() == null) {
            return toOrderDTO(order);
        } else {
            return delegatedOrder(order.getNo(), orderAddCommand.getOrderDelegatedCommand());
        }
    }

    @Override
    public OrderDTO modifyOrder(OrderModifyCommand orderModifyCommand) {
        Order order = orderRepository.of(orderModifyCommand.getOrderId());
        order.modify(orderModifyCommand.getOrderNo(),
                orderModifyCommand.getOrderTime(),
                orderModifyCommand.getGoodsWeight(),
                orderModifyCommand.getGoodsQuantity(),
                orderModifyCommand.getIncomingChannel(),
                orderModifyCommand.getComment(),
                orderModifyCommand.getToAddress(),
                orderModifyCommand.getDelegateOrderNo());
        order = orderRepository.save(order);
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO searchOrder(String orderNo) {
        return getOrder(orderNo);
    }

    @Override
    public List<OrderDTO> searchOrders(List<String> orderNos) {
        return orderNos.stream()
                .map(this::searchOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO settleOrder(String orderNo) {
        Order order = orderRepository.of(orderNo);
        if (order == null) {
            throw OrderNotFoundException.orderNo(orderNo);
        }
        order.settle();
        orderRepository.save(order);
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO delegatedOrder(String orderNo, OrderDelegatedCommand orderDelegatedCommand) {
        Order order = orderRepository.of(orderNo);
        if (order == null) {
            throw OrderNotFoundException.orderNo(orderNo);
        }
        OrderDelegatedCommand.DelegateItem delegateItem = orderDelegatedCommand.getDelegateItem();
        Goods delegateGoods = order.getGoods(delegateItem.getDelegateGoodsId());
        //goodsId不合法理应报异常，目前为了方便前端传参
        delegateGoods = delegateGoods != null ? delegateGoods : order.getGoods().get(0);
        DelegateOrder delegateOrders = new DelegateOrder(
                null,
                delegateItem.getDelegateOrderNo(),
                delegateItem.getDelegateCorporateName(),
                delegateItem.getDelegateAmount(),
                delegateItem.getDelegateTime(),
                delegateGoods
        );
        order.delegated(delegateOrders);
        orderRepository.save(order);
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO getOrder(String orderNo) {
        Order order = orderRepository.of(orderNo);
        if (order == null) {
            throw OrderNotFoundException.orderNo(orderNo);
        }
        return toOrderDTO(order);
    }

    @Override
    public PageDTO<OrderDTO> getOrderPage(int page, int pageSize) {
        List<OrderDTO> orders = orderRepository.pageOf(page, pageSize).stream()
                .map(OrderApplicationServiceImpl::toOrderDTO)
                .collect(Collectors.toList());
        return PageDTO.by(orders);
    }

    @Override
    public PageDTO<OrderDTO> getFirstOrderPage() {
        List<OrderDTO> orders = orderRepository.firstPage().stream()
                .map(OrderApplicationServiceImpl::toOrderDTO)
                .collect(Collectors.toList());
        return PageDTO.by(orders);
    }

    private static OrderDTO toOrderDTO(Order order) {
        return OrderDTOMapper.INSTANCE.toDTO(order);
    }
}
