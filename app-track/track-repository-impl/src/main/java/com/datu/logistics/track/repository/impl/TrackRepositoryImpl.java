package com.datu.logistics.track.repository.impl;

import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TrackRepositoryImpl implements TrackRepository {
    @Override
    public Track trackOf(BigInteger id) {
        return new Track(id);
    }
}
