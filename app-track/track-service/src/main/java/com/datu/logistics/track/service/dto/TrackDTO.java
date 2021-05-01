package com.datu.logistics.track.service.dto;

import lombok.*;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {
    @NonNull
    private BigInteger trackId;
}