package com.datu.logistics.track.view.vo;

import com.datu.logistics.order.service.dto.OrderDTO;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrdersTracksVO {
    private List<OrderVO> orders;
    private List<TracksVO> tracks;

    public OrdersTracksVO(List<TracksDTO> tracksDTOS) {
        this.orders = tracksDTOS.stream()
                .map(it -> new OrderVO(it.getOrder()))
                .collect(Collectors.toList());
        this.tracks = tracksDTOS.stream()
                .map(TracksVO::new)
                .collect(Collectors.toList());
    }

    @Data
    public static class OrderVO {
        private String orderNo;
        private String delegateOrderNo;
        private Date orderTime;
        private String destination;

        public OrderVO(OrderDTO orderDTO) {
            this.orderNo = orderDTO.getNo();
            this.delegateOrderNo = orderDTO.getDelegateOrders().isEmpty() ? null : orderDTO.getDelegateOrders().get(0).getNo();
            this.orderTime = orderDTO.getTime();
            this.destination = orderDTO.getTo().getAddress();
        }
    }

    @Data
    public static class TracksVO {
        private String orderNo;
        private List<TrackVO> tracks;

        public TracksVO(TracksDTO tracksDTO) {
            this.orderNo = tracksDTO.getOrder().getNo();
            this.tracks = tracksDTO.getTracks().stream()
                    .map(TrackVO::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class TrackVO {
        private Date trackTime;
        private String trackArea;
        private String trackEvent;

        public TrackVO(TrackDTO trackDTO) {
            this.trackTime = trackDTO.getTime();
            this.trackArea = trackDTO.getArea();
            this.trackEvent = trackDTO.getEvent();
        }
    }
}
