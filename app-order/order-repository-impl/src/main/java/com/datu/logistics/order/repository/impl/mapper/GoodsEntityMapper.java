package com.datu.logistics.order.repository.impl.mapper;

import com.datu.logistics.order.domain.model.Goods;
import com.datu.logistics.order.repository.impl.dao.entity.GoodsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GoodsEntityMapper {
    GoodsEntityMapper INSTANCE = Mappers.getMapper(GoodsEntityMapper.class);

    @Mapping(target = "orderEntity", ignore = true)
    GoodsEntity toEntity(Goods goods);

    Goods toModel(GoodsEntity entity);
}
