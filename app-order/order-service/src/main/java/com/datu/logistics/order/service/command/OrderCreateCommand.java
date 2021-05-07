package com.datu.logistics.order.service.command;

import com.datu.logistics.order.service.dto.ContactsDTO;
import com.datu.logistics.order.service.dto.GoodsDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public final class OrderCreateCommand {
    private String orderNo;
    private Date orderTime;
    private int orderAmountPaid;
    private ContactsDTO from;
    private ContactsDTO to;
    private GoodsDTO goods;
}
