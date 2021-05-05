package com.datu.logistics.track.service.dto;

import com.datu.logistics.order.service.dto.OrderDTO;
import lombok.Data;

import java.util.List;

@Data
public class TracksDTO {
    private OrderDTO order;
    private List<TrackDTO> tracks;
}
