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
    private final String orderNo;

    public static Track create(Long trackId, String trackArea, String trackEvent, String orderNo) {
        return new Track(trackId, trackArea, trackEvent, new Date(), orderNo);
    }
}
