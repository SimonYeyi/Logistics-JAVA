package com.datu.logistics.track.service.command;

import lombok.Data;

import java.util.Date;

@Data
public class TrackModifyCommand {
    private long trackId;
    private Date time;
    private String trackArea;
    private String trackEvent;
}
