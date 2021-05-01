package com.datu.logistics.track.view.vo;

import com.datu.logistics.track.service.dto.TrackDTO;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TrackVO {
    private final BigInteger trackId;

    public TrackVO(TrackDTO trackDTO) {
        this.trackId = trackDTO.getTrackId();
    }
}
