package com.datu.logistics.track.service.impl;

import com.datu.logistics.feign.RestService;
import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.TrackRepository;
import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.command.TrackAddCommand;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@RestService
public class TrackApplicationServiceImpl implements TrackApplicationService {
    private final TrackRepository trackRepository;
    private final OrderApplicationService orderApplicationService;

    public TrackApplicationServiceImpl(TrackRepository trackRepository, OrderApplicationService orderApplicationService) {
        this.trackRepository = trackRepository;
        this.orderApplicationService = orderApplicationService;
    }

    @Override
    public TrackDTO addTrack(TrackAddCommand trackAddCommand) {
        Track track = Track.add(
                trackAddCommand.getTrackArea(),
                trackAddCommand.getTrackEvent(),
                trackAddCommand.getOrderId()
        );
        track = trackRepository.save(track);
        return toTrackDTO(track);
    }

    @Override
    public TracksDTO searchTracks(String orderNo) {
        return getTracks(orderNo);
    }

    @Override
    public List<TracksDTO> searchMultiTracks(List<String> orderNos) {
        return orderApplicationService.searchOrders(orderNos).stream()
                .map(this::getTracks)
                .collect(Collectors.toList());
    }

    @Override
    public TracksDTO getTracks(String orderNo) {
        OrderDTO orderDTO = orderApplicationService.getOrder(orderNo);
        return getTracks(orderDTO);
    }

    private TracksDTO getTracks(OrderDTO orderDTO) {
        List<TrackDTO> trackDTOs = trackRepository.tracks(orderDTO.getId()).stream()
                .map(TrackApplicationServiceImpl::toTrackDTO)
                .collect(Collectors.toList());
        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.setOrder(orderDTO);
        tracksDTO.setTracks(trackDTOs);
        return tracksDTO;
    }

    private static TrackDTO toTrackDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        BeanCopier.create(Track.class, TrackDTO.class, false)
                .copy(track, trackDTO, null);
        return trackDTO;
    }
}
