package com.datu.logistics.track.service.command;

import lombok.Data;

@Data
public class TrackAddCommand {
    private long orderId;
    private String trackArea;
    private String trackEvent;
}
