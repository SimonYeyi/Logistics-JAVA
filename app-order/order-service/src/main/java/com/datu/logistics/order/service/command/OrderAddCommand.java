package com.datu.logistics.order.service.command;

import com.datu.logistics.order.service.dto.ContactsDTO;
import com.datu.logistics.order.service.dto.GoodsDTO;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public final class OrderAddCommand {
    private String orderNo;
    private Date orderTime;
    private int orderAmountPaid;
    private ContactsDTO from;
    private ContactsDTO to;
    private List<GoodsDTO> goodsList;

    public OrderAddCommand() {
        from = new ContactsDTO();
        from.setFullName("");
        from.setPhone("");
        from.setAddress("");
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setName("generated");
        goodsList = Collections.singletonList(goodsDTO);
    }
}
