package com.datu.logistics.order.domain.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@ToString
@RequiredArgsConstructor
public class Order {
    @NonNull
    private final BigInteger id;
}
