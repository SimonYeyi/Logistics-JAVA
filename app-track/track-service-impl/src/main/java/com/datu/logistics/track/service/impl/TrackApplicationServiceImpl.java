package com.datu.logistics.track.service.impl;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Primary
@RestController
public class TrackApplicationServiceImpl implements TrackApplicationService {
    private final TrackRepository trackRepository;
    private final OrderApplicationService orderApplicationService;

    public TrackApplicationServiceImpl(TrackRepository trackRepository, OrderApplicationService orderApplicationService) {
        this.trackRepository = trackRepository;
        this.orderApplicationService = orderApplicationService;
    }

    @Override
    public TrackDTO createTrack(String orderNo, TrackDTO trackDTO) {
        Track track = Track.create(
                trackDTO.getId(),
                trackDTO.getArea(),
                trackDTO.getEvent(),
                orderNo
        );
        track = trackRepository.save(track);
        return toTrackDTO(track);
    }

    @Override
    public TracksDTO searchTracks(String orderNo) {
        OrderDTO orderDTO = orderApplicationService.searchOrder(orderNo);
        List<TrackDTO> trackDTOs = trackRepository.tracks(orderNo).stream()
                .map(TrackApplicationServiceImpl::toTrackDTO)
                .collect(Collectors.toList());
        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.setOrder(orderDTO);
        tracksDTO.setTracks(trackDTOs);
        return tracksDTO;
    }

    @Override
    public List<TracksDTO> searchMultiTracks(List<String> orderNos) {
        return orderNos.stream()
                .map(this::searchTracks)
                .collect(Collectors.toList());
    }

    private static TrackDTO toTrackDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        BeanCopier.create(Track.class, TrackDTO.class, false)
                .copy(track, trackDTO, null);
        return trackDTO;
    }
}
