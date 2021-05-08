package com.datu.logistics.order.service.command;

import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public final class OrderDelegatedCommand {
    private List<DelegateItem> delegateItems = Collections.emptyList();

    @Data
    public static final class DelegateItem {
        private String delegateOrderNo;
        private String delegateCorporateName;
        private int delegateAmount;
        private Date delegateTime;
        private long delegateGoodsId;

        public DelegateItem() {
            delegateCorporateName = "";
            delegateTime = new Date();
        }
    }
}
