package com.datu.logistics.track.repository.impl.mapper;

import com.datu.logistics.track.model.Track;
import com.datu.logistics.track.repository.impl.dao.entity.TrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TrackEntityMapper {
    TrackEntityMapper INSTANCE = Mappers.getMapper(TrackEntityMapper.class);

    TrackEntity toEntity(Track model);

    Track toModel(TrackEntity entity);
}
