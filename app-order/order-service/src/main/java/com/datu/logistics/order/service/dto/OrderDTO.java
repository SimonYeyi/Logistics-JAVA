package com.datu.logistics.order.service.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public final class OrderDTO {
    private String no;
    private Date time;
    private int amount;
    private int amountPaid;
    private ContactsDTO from;
    private ContactsDTO to;
    private List<GoodsDTO> goods;
    private List<DelegateOrderDTO> delegateOrders;
}
