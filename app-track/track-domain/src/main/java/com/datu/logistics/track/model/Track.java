package com.datu.logistics.track.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@ToString
@RequiredArgsConstructor
public class Track {
    @NonNull
    private final BigInteger id;
}
