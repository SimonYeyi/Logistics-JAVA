package com.datu.logistics.order.repository.impl.mapper;

import com.datu.logistics.order.domain.model.DelegateOrder;
import com.datu.logistics.order.repository.impl.dao.entity.DelegateOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {GoodsEntityMapper.class})
public interface DelegateOrderEntityMapper {
    DelegateOrderEntityMapper INSTANCE = Mappers.getMapper(DelegateOrderEntityMapper.class);

    @Mapping(source = "goods",target = "goodsEntity")
    @Mapping(target = "orderEntity", ignore = true)
    DelegateOrderEntity toEntity(DelegateOrder delegateOrder);

    @Mapping(source = "goodsEntity", target = "goods")
    DelegateOrder toModel(DelegateOrderEntity entity);
}
