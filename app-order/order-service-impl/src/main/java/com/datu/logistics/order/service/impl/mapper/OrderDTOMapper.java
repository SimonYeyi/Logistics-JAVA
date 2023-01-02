package com.datu.logistics.order.service.impl.mapper;

import com.datu.logistics.order.domain.model.Contacts;
import com.datu.logistics.order.domain.model.Goods;
import com.datu.logistics.order.domain.model.Order;
import com.datu.logistics.order.service.dto.ContactsDTO;
import com.datu.logistics.order.service.dto.GoodsDTO;
import com.datu.logistics.order.service.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderDTOMapper {
    OrderDTOMapper INSTANCE = Mappers.getMapper(OrderDTOMapper.class);

    OrderDTO toDTO(Order order);

    Goods toGoods(GoodsDTO goods);

    Contacts toContacts(ContactsDTO contacts);
}
