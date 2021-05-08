package com.datu.logistics.track.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class Track {
    private final Long id;
    private final String area;
    private final String event;
    private final Date time;
    private final Long orderId;

    public static Track create(String trackArea, String trackEvent, Long orderId) {
        return new Track(null, trackArea, trackEvent, new Date(), orderId);
    }
}
