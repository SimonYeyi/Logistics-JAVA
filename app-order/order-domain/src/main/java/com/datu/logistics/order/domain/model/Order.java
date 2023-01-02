package com.datu.logistics.order.domain.model;

import lombok.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Order implements Comparable<Order> {
    private final Long id;
    private String no;
    private int amountPaid;
    private Date time;
    private Float goodsWeight;
    private Integer goodsQuantity;
    private String incomingChannel;
    private String comment;
    private final Contacts from;
    private Contacts to;
    private final List<Goods> goods;
    private List<DelegateOrder> delegateOrders = Collections.emptyList();

    public static Order add(String orderNo,
                            int orderAmountPaid,
                            Date orderTime,
                            Float goodsWeight,
                            Integer goodsQuantity,
                            String incomingChannel,
                            String comment,
                            Contacts from,
                            Contacts to,
                            List<Goods> goodsList) {
        Order order = new Order(null, orderNo, 0, orderTime, goodsWeight, goodsQuantity, incomingChannel, comment, from, to, goodsList, Collections.emptyList());
        order.amountPaid = orderAmountPaid;
        return order;
    }

    public void delegated(DelegateOrder delegateOrder) {
        this.delegateOrders = Collections.singletonList(delegateOrder);
    }

    public void settle() {
        this.amountPaid = getAmount();
    }

    public void modify(String no,
                       Date time,
                       Float goodsWeight,
                       Integer goodsQuantity,
                       String incomingChannel,
                       String comment,
                       String toAddress,
                       String delegateOrderNo) {
        this.no = no;
        this.time = time;
        this.goodsWeight = goodsWeight;
        this.goodsQuantity = goodsQuantity;
        this.incomingChannel = incomingChannel;
        this.comment = comment;
        this.to = new Contacts(to.getFullName(), to.getPhone(), toAddress);
        this.delegateOrders.get(0).modify(delegateOrderNo);
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
