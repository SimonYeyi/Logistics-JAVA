package com.datu.logistics.order.service.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class OrderDTO {
    @NonNull
    private BigInteger orderId;
}