package com.datu.logistics.track.view.mapper;

import com.datu.logistics.order.service.dto.DelegateOrderDTO;
import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import com.datu.logistics.track.view.vo.OrdersTracksVO;
import io.lettuce.core.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrdersTracksVOMapper {
    OrdersTracksVOMapper INSTANCE = Mappers.getMapper(OrdersTracksVOMapper.class);

    @Mapping(source = "value", target = "orders")
    @Mapping(source = "value", target = "tracks")
    OrdersTracksVO toOrdersTracksVO(Value<List<TracksDTO>> tracksDTOValue);

    @Mapping(source = "area", target = "trackArea")
    @Mapping(source = "event", target = "trackEvent")
    @Mapping(source = "time", target = "trackTime")
    OrdersTracksVO.TrackVO _toVO(TrackDTO trackDTO);

    @Mapping(source = "order.no", target = "orderNo")
    OrdersTracksVO.TracksVO _toTracksVO(TracksDTO tracksDTO);

    @Mapping(source = "order.no", target = "orderNo")
    @Mapping(source = "order", target = "delegateOrderNo", qualifiedByName = "toDelegateOrderNo")
    @Mapping(source = "order.time", target = "orderTime")
    @Mapping(source = "order.to.address", target = "destination")
    OrdersTracksVO.OrderVO _toOrderVO(TracksDTO tracks);

    @Named("toDelegateOrderNo")
    default String _toDelegateOrderNo(OrderDTO order) {
        return order.getDelegateOrders().stream()
                .findFirst().map(DelegateOrderDTO::getNo).orElse(null);
    }
}
