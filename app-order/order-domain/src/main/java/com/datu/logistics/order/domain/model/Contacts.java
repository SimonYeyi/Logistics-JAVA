package com.datu.logistics.order.domain.model;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class Contacts {
    private final String fullName;
    private final String phone;
    private final String address;
}
