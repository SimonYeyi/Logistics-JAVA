package com.datu.logistics.order.domain.model;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public final class Goods {
    private long id;
    private final String name;
    private final int weight;
    private final int volume;
    private final int amount;

    public void saved(long id) {
        this.id = id;
    }
}
