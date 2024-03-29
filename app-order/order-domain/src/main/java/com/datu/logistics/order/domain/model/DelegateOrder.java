package com.datu.logistics.order.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class DelegateOrder {
    private final Long id;
    private String no;
    private final String corporateName;
    private final int amount;
    private final Date time;
    private final Goods goods;

    public void modify(String no) {
        this.no = no;
    }
}
