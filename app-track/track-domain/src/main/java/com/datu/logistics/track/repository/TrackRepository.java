package com.datu.logistics.track.repository;

import com.datu.logistics.track.model.Track;

import java.math.BigInteger;
import java.util.List;

public interface TrackRepository {

    Track save(Track track);

    List<Track> tracks(String orderNo);
}
