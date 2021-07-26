package com.datu.logistics.track.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class Track {
    private final Long id;
    private String area;
    private String event;
    private Date time;
    private final Long orderId;

    public static Track add(String trackArea, String trackEvent, Date time, Long orderId) {
        return new Track(null, trackArea, trackEvent, time, orderId);
    }

    public void modify(String area, String event, Date time) {
        this.area = area;
        this.event = event;
        this.time = time;
    }
}
