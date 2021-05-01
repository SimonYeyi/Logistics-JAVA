package com.datu.logistics.track.service.impl;

import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TrackDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@Primary
@RestController
public class TrackApplicationServiceImpl implements TrackApplicationService {

    @Resource
    private TrackRepository trackRepository;

    @Override
    public TrackDTO searchTrack(BigInteger trackId) {
        Track track = trackRepository.trackOf(trackId);
        return new TrackDTO(track.getId());
    }
}
