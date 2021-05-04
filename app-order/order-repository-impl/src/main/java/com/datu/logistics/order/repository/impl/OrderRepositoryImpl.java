package com.datu.logistics.order.repository.impl;

import com.datu.logistics.order.domain.model.Contacts;
import com.datu.logistics.order.domain.model.DelegateOrder;
import com.datu.logistics.order.domain.model.Goods;
import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.domain.repository.OrderRepository;
import com.datu.logistics.order.repository.impl.dao.OrderDAO;
import com.datu.logistics.order.repository.impl.dao.entity.DelegateOrderEntity;
import com.datu.logistics.order.repository.impl.dao.entity.GoodsEntity;
import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order orderOf(String id) {
        Optional<OrderEntity> orderEntity = orderDAO.findById(id);
        return orderEntity.map(OrderRepositoryImpl::toOrder).orElse(null);
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = orderDAO.saveAndFlush(toOrderEntity(order));
        return toOrder(orderEntity);
    }

    private static OrderEntity toOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setNo(order.getNo());
        orderEntity.setAmount(order.getAmount());
        orderEntity.setAmountPaid(order.getAmountPaid());
        orderEntity.setTime(order.getTime());
        orderEntity.setFromFullName(order.getFrom().getFullName());
        orderEntity.setFromPhone(order.getFrom().getPhone());
        orderEntity.setFromAddress(order.getFrom().getAddress());
        orderEntity.setToFullName(order.getTo().getFullName());
        orderEntity.setToPhone(order.getTo().getPhone());
        orderEntity.setToAddress(order.getTo().getAddress());
        orderEntity.setDelegateOrders(
                order.getDelegateOrders().stream().map(it -> {
                    DelegateOrderEntity delegateOrderEntity = new DelegateOrderEntity();
                    delegateOrderEntity.setNo(it.getNo());
                    delegateOrderEntity.setCorporateName(it.getCorporateName());
                    delegateOrderEntity.setAmount(it.getAmount());
                    delegateOrderEntity.setTime(it.getTime());
                    delegateOrderEntity.setOrderNo(order.getNo());
                    GoodsEntity goodsEntity = toGoodsEntity(it.getGoods(), order.getNo());
                    delegateOrderEntity.setGoodsEntity(goodsEntity);
                    return delegateOrderEntity;
                }).collect(Collectors.toSet())
        );
        Set<GoodsEntity> goodsEntities = order.getGoods().stream()
                .map(it -> toGoodsEntity(it, order.getNo()))
                .collect(Collectors.toSet());
        orderEntity.setGoodsEntities(goodsEntities);
        return orderEntity;
    }

    public static Order toOrder(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getNo(),
                orderEntity.getAmountPaid(),
                orderEntity.getTime(),
                new Contacts(orderEntity.getFromFullName(),
                        orderEntity.getFromPhone(),
                        orderEntity.getFromAddress()),
                new Contacts(orderEntity.getToFullName(),
                        orderEntity.getToPhone(),
                        orderEntity.getToAddress()),
                orderEntity.getGoodsEntities().stream()
                        .map(OrderRepositoryImpl::toGoods)
                        .collect(Collectors.toList()),
                orderEntity.getDelegateOrders().stream()
                        .map(it -> new DelegateOrder(
                                it.getNo(),
                                it.getCorporateName(),
                                it.getAmount(),
                                it.getTime(),
                                toGoods(it.getGoodsEntity())
                        )).collect(Collectors.toList()));
    }

    private static Goods toGoods(GoodsEntity goodsEntity) {
        return new Goods(
                goodsEntity.getId(),
                goodsEntity.getName(),
                goodsEntity.getWeight(),
                goodsEntity.getVolume(),
                goodsEntity.getAmount()
        );
    }

    private static GoodsEntity toGoodsEntity(Goods goods, String orderNo) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(goods.getId());
        goodsEntity.setName(goods.getName());
        goodsEntity.setWeight(goods.getWeight());
        goodsEntity.setVolume(goods.getVolume());
        goodsEntity.setAmount(goods.getAmount());
        goodsEntity.setOrderNo(orderNo);
        return goodsEntity;
    }
}
