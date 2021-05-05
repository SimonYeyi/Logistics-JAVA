package com.datu.logistics.track.repository.impl;

import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import com.datu.logistics.track.repository.impl.dao.TrackDAO;
import com.datu.logistics.track.repository.impl.dao.entity.TrackEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrackRepositoryImpl implements TrackRepository {
    private final TrackDAO trackDAO;

    public TrackRepositoryImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public Track save(Track track) {
        TrackEntity trackEntity = toTrackEntity(track);
        trackEntity = trackDAO.saveAndFlush(trackEntity);
        return toTrack(trackEntity);
    }

    @Override
    public List<Track> tracks(String orderNo) {
        return trackDAO.findAllByOrderNo(orderNo).stream()
                .map(TrackRepositoryImpl::toTrack)
                .collect(Collectors.toList());
    }

    private static TrackEntity toTrackEntity(Track track) {
        TrackEntity trackEntity = new TrackEntity();
        trackEntity.setId(track.getId());
        trackEntity.setArea(track.getArea());
        trackEntity.setEvent(track.getEvent());
        trackEntity.setTime(track.getTime());
        trackEntity.setOrderNo(track.getOrderNo());
        return trackEntity;
    }

    private static Track toTrack(TrackEntity trackEntity) {
        return new Track(
                trackEntity.getId(),
                trackEntity.getArea(),
                trackEntity.getEvent(),
                trackEntity.getTime(),
                trackEntity.getOrderNo()
        );
    }
}
