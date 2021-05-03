package com.datu.logistics.order.service.dto;

import lombok.*;

import java.util.Date;

@Data
public final class DelegateOrderDTO {
    private String no;
    private String corporateName;
    private int amount;
    private Date time;
    private GoodsDTO goods;
}
