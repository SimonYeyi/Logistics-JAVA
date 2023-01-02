package com.datu.logistics.track.service.command;

import lombok.Data;

import java.util.Date;

@Data
public class TrackAddCommand {
    private long orderId;
    private Date trackTime;
    private String trackArea;
    private String trackEvent;
}
