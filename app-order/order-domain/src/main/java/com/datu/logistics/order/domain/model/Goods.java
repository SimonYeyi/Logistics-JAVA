package com.datu.logistics.order.domain.model;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public final class Goods {
    private final Long id;
    private final String name;
    private final int weight;
    private final int volume;
    private final int amount;
}
