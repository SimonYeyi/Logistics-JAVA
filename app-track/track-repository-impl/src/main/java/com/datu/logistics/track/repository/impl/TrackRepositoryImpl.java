package com.datu.logistics.track.repository.impl;

import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import com.datu.logistics.track.repository.impl.dao.TrackDAO;
import com.datu.logistics.track.repository.impl.dao.entity.TrackEntity;
import com.datu.logistics.track.repository.impl.mapper.TrackEntityMapper;
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
        TrackEntity trackEntity = TrackEntityMapper.INSTANCE.toEntity(track);
        trackEntity = trackDAO.saveAndFlush(trackEntity);
        return TrackEntityMapper.INSTANCE.toModel(trackEntity);
    }

    @Override
    public Track of(long id) {
        TrackEntity entity = trackDAO.findById(id).orElse(null);
        return TrackEntityMapper.INSTANCE.toModel(entity);
    }

    @Override
    public List<Track> list(Long orderId) {
        return trackDAO.findAllByOrderId(orderId).stream()
                .map(TrackEntityMapper.INSTANCE::toModel)
                .collect(Collectors.toList());
    }
}
