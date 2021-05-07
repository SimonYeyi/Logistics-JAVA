package com.datu.logistics.order.service.command;

import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public final class OrderDelegatedCommand {
    private String delegateOrderNo;
    private String delegateCorporateName;
    private int delegateAmount;
    private Date delegateTime;
}
