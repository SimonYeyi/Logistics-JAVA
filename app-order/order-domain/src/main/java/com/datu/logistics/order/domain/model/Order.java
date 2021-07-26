package com.datu.logistics.order.domain.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Order implements Comparable<Order> {
    private final Long id;
    private String no;
    private int amountPaid;
    private Date time;
    private final Contacts from;
    private Contacts to;
    private final List<Goods> goods;
    private List<DelegateOrder> delegateOrders = Collections.emptyList();

    public static Order add(String orderNo, int orderAmountPaid, Date orderTime, Contacts from, Contacts to, List<Goods> goodsList) {
        Order order = new Order(null, orderNo, 0, orderTime, from, to, goodsList, Collections.emptyList());
        order.amountPaid = orderAmountPaid;
        return order;
    }

    public void delegated(List<DelegateOrder> delegateOrders) {
        this.delegateOrders = delegateOrders;
    }

    public void settle() {
        this.amountPaid = getAmount();
    }

    public void modify(String no, Date time, String toAddress) {
        this.no = no;
        this.time = time;
        this.to = new Contacts(to.getFullName(), to.getPhone(), toAddress);
    }

    public int getAmount() {
        return goods.stream().mapToInt(Goods::getAmount).sum();
    }

    public Goods getGoods(long goodsId) {
        return goods.stream().filter(it -> it.getId() == goodsId).findFirst().orElse(null);
    }

    @Override
    public int compareTo(Order o) {
        return time.compareTo(o.getTime());
    }
}
