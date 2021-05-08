package com.datu.logistics.track.service.command;

import lombok.Data;

@Data
public class TrackCreateCommand {
    private long orderId;
    private String trackArea;
    private String trackEvent;
}
