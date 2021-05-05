package com.datu.logistics.track.service.dto;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Data
public class TrackDTO {
    private Long id;
    private String area;
    private String event;
    private Date time;
}