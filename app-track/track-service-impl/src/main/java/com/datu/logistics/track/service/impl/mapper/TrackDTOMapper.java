package com.datu.logistics.track.service.impl.mapper;

import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TrackDTOMapper {
    TrackDTOMapper INSTANCE = Mappers.getMapper(TrackDTOMapper.class);

    TrackDTO toDTO(Track model);

    TracksDTO toDTOs(OrderDTO order, List<Track> tracks);
}
