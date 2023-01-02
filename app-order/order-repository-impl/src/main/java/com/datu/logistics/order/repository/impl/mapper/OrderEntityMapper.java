package com.datu.logistics.order.repository.impl.mapper;

import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.repository.impl.dao.entity.OrderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, uses = {DelegateOrderEntityMapper.class, GoodsEntityMapper.class})
public interface OrderEntityMapper {
    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    @Mapping(source = "goods", target = "goodsEntities")
    @InheritInverseConfiguration
    OrderEntity toEntity(Order order);

    @Mapping(source = "fromFullName", target = "from.fullName")
    @Mapping(source = "fromPhone", target = "from.phone")
    @Mapping(source = "fromAddress", target = "from.address")
    @Mapping(source = "toFullName", target = "to.fullName")
    @Mapping(source = "toPhone", target = "to.phone")
    @Mapping(source = "toAddress", target = "to.address")
    @Mapping(source = "goodsEntities", target = "goods")
    Order toModel(OrderEntity entity);
}
