package com.datu.logistics.order.service.dto;

import lombok.*;

@Data
public final class GoodsDTO {
    private Long id;
    private String name;
    private int weight;
    private int volume;
    private int amount;
}
