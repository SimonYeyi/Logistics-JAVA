package com.datu.logistics.order.view.mapper;

import com.datu.logistics.order.service.dto.DelegateOrderDTO;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.order.view.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderVOMapper {
    OrderVOMapper INSTANCE = Mappers.getMapper(OrderVOMapper.class);

    @Mapping(source = "no", target = "orderNo")
    @Mapping(source = ".", target = "delegateOrderNo", qualifiedByName = "toDelegateOrderNo")
    @Mapping(source = "time", target = "orderTime")
    @Mapping(source = "to.address", target = "destination")
    OrderVO toVO(OrderDTO order);

    @Named("toDelegateOrderNo")
    default String _toDelegateOrderNo(OrderDTO order) {
        return order.getDelegateOrders().stream()
                .findFirst().map(DelegateOrderDTO::getNo).orElse(null);
    }
}
