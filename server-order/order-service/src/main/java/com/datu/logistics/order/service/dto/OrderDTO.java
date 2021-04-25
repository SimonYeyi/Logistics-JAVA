package com.datu.logistics.order.service.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @NonNull
    private BigInteger orderId;
}