package com.datu.logistics.order.domain.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class Order {
    private final String id;
    private final String no;
    private int amountPaid;
    private final Date time;
    private final Contacts from;
    private final Contacts to;
    private final List<Goods> goods;
    private List<DelegateOrder> delegateOrders;

    public Order(String no, int amountPaid, Date time, Contacts from, Contacts to, List<Goods> goods, List<DelegateOrder> delegateOrders) {
        this.id = no;
        this.no = no;
        this.amountPaid = amountPaid;
        this.time = time;
        this.from = from;
        this.to = to;
        this.goods = goods;
        this.delegateOrders = delegateOrders;
    }

    public static Order create(String orderNo, int orderAmountPaid, Date orderTime, Contacts from, Contacts to, List<Goods> goodsList) {
        Order order = new Order(orderNo, orderNo, orderTime, from, to, goodsList);
        order.amountPaid = orderAmountPaid;
        return order;
    }

    public void delegated(List<DelegateOrder> delegateOrders) {
        this.delegateOrders = delegateOrders;
    }

    public void settle() {
        this.amountPaid = getAmount();
    }

    public int getAmount() {
        return goods.stream().mapToInt(Goods::getAmount).sum();
    }

    public Goods getGoods(long goodsId) {
        return goods.stream().filter(it -> it.getId() == goodsId).findFirst().orElse(null);
    }
}
