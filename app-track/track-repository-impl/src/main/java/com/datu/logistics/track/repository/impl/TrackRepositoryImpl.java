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
    public Track of(long id) {
        TrackEntity entity = trackDAO.findById(id).orElse(null);
        return toTrack(entity);
    }

    @Override
    public List<Track> list(Long orderId) {
        return trackDAO.findAllByOrderId(orderId).stream()
                .map(TrackRepositoryImpl::toTrack)
                .collect(Collectors.toList());
    }

    private static TrackEntity toTrackEntity(Track track) {
        TrackEntity trackEntity = new TrackEntity();
        trackEntity.setId(track.getId());
        trackEntity.setArea(track.getArea());
        trackEntity.setEvent(track.getEvent());
        trackEntity.setTime(track.getTime());
        trackEntity.setOrderId(track.getOrderId());
        return trackEntity;
    }

    private static Track toTrack(TrackEntity trackEntity) {
        if (trackEntity == null) return null;
        return new Track(
                trackEntity.getId(),
                trackEntity.getArea(),
                trackEntity.getEvent(),
                trackEntity.getTime(),
                trackEntity.getOrderId()
        );
    }
}
