package com.datu.logistics.track.repository;

import com.datu.logistics.track.model.Track;

import java.math.BigInteger;

public interface TrackRepository {
    Track trackOf(BigInteger id);
}
