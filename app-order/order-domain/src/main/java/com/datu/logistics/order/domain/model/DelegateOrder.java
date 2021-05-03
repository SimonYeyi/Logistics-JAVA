package com.datu.logistics.order.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class DelegateOrder {
    private final String id;
    private final String no;
    private final String corporateName;
    private final int amount;
    private final Date time;
    private final Goods goods;

    public DelegateOrder(String no, String corporateName, int amount, Date time, Goods goods) {
        this.id = no;
        this.no = no;
        this.corporateName = corporateName;
        this.amount = amount;
        this.time = time;
        this.goods = goods;
    }
}
