package com.datu.logistics.order.service.impl;

import com.datu.logistics.order.domain.model.Contacts;
import com.datu.logistics.order.domain.model.DelegateOrder;
import com.datu.logistics.order.domain.model.Goods;
import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.command.OrderCreateCommand;
import com.datu.logistics.order.service.command.OrderDelegatedCommand;
import com.datu.logistics.order.service.dto.ContactsDTO;
import com.datu.logistics.order.service.dto.DelegateOrderDTO;
import com.datu.logistics.order.service.dto.GoodsDTO;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.service.exception.OrderNotFoundException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@RestController
public class OrderApplicationServiceImpl implements OrderApplicationService {

    @Resource
    private final OrderRepository orderRepository;

    public OrderApplicationServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO createOrder(OrderCreateCommand orderCreateCommand) {
        Order order = Order.create(
                orderCreateCommand.getOrderNo(),
                orderCreateCommand.getOrderAmountPaid(),
                orderCreateCommand.getOrderTime(),
                toContacts(orderCreateCommand.getFrom()),
                toContacts(orderCreateCommand.getTo()),
                orderCreateCommand.getGoods().stream()
                        .map(OrderApplicationServiceImpl::toGoods)
                        .collect(Collectors.toList())
        );
        order = orderRepository.save(order);
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO searchOrder(String orderNo) {
        Order order = orderRepository.orderOf(orderNo);
        if (order == null) {
            throw new OrderNotFoundException(orderNo);
        }
        return toOrderDTO(order);
    }

    @Override
    public List<OrderDTO> searchOrders(List<String> orderNos) {
        return orderNos.stream()
                .map(this::searchOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO settleOrder(String orderNo) {
        Order order = orderRepository.orderOf(orderNo);
        if (order == null) {
            throw new OrderNotFoundException(orderNo);
        }
        order.settle();
        orderRepository.save(order);
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO delegatedOrder(String orderNo, OrderDelegatedCommand orderDelegatedCommands) {
        Order order = orderRepository.orderOf(orderNo);
        if (order == null) {
            throw new OrderNotFoundException(orderNo);
        }
        List<DelegateOrder> delegateOrders = orderDelegatedCommands.getDelegateItems().stream()
                .map(delegateItem -> new DelegateOrder(
                        null,
                        delegateItem.getDelegateOrderNo(),
                        delegateItem.getDelegateCorporateName(),
                        delegateItem.getDelegateAmount(),
                        delegateItem.getDelegateTime(),
                        order.getGoods(delegateItem.getDelegateGoodsId())
                ))
                .collect(Collectors.toList());
        order.delegated(delegateOrders);
        orderRepository.save(order);
        return toOrderDTO(order);
    }

    private static Goods toGoods(GoodsDTO goodsDTO) {
        return new Goods(
                goodsDTO.getId(),
                goodsDTO.getName(),
                goodsDTO.getWeight(),
                goodsDTO.getVolume(),
                goodsDTO.getAmount());
    }

    private static Contacts toContacts(ContactsDTO contactsDTO) {
        return new Contacts(
                contactsDTO.getPhone(),
                contactsDTO.getFullName(),
                contactsDTO.getAddress()
        );
    }

    private static OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        BeanCopier.create(Order.class, OrderDTO.class, false)
                .copy(order, orderDTO, null);

        orderDTO.setAmount(order.getAmount());

        ContactsDTO from = new ContactsDTO();
        BeanCopier.create(Contacts.class, ContactsDTO.class, false)
                .copy(order.getFrom(), from, null);
        orderDTO.setFrom(from);

        ContactsDTO to = new ContactsDTO();
        BeanCopier.create(Contacts.class, ContactsDTO.class, false)
                .copy(order.getTo(), to, null);
        orderDTO.setTo(to);

        List<GoodsDTO> goodsDTOS = order.getGoods().stream().map(good -> {
            GoodsDTO goodsDTO = new GoodsDTO();
            BeanCopier.create(Goods.class, GoodsDTO.class, false)
                    .copy(good, goodsDTO, null);
            return goodsDTO;
        }).collect(Collectors.toList());
        orderDTO.setGoods(goodsDTOS);

        List<DelegateOrderDTO> delegateOrderDTOS = order.getDelegateOrders().stream().map(delegateOrder -> {
            DelegateOrderDTO delegateOrderDTO = new DelegateOrderDTO();
            BeanCopier.create(DelegateOrder.class, DelegateOrderDTO.class, false)
                    .copy(delegateOrder, delegateOrderDTO, null);
            GoodsDTO goodsDTO = new GoodsDTO();
            BeanCopier.create(Goods.class, GoodsDTO.class, false)
                    .copy(delegateOrder.getGoods(), goodsDTO, null);
            delegateOrderDTO.setGoods(goodsDTO);
            return delegateOrderDTO;
        }).collect(Collectors.toList());
        orderDTO.setDelegateOrders(delegateOrderDTOS);

        return orderDTO;
    }
}
