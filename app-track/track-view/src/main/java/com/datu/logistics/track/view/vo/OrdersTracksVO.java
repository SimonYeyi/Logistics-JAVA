package com.datu.logistics.track.view.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdersTracksVO {
    private List<OrderVO> orders;
    private List<TracksVO> tracks;

    @Data
    public static class OrderVO {
        private String orderNo;
        private String delegateOrderNo;
        private Date orderTime;
        private String destination;
    }

    @Data
    public static class TracksVO {
        private String orderNo;
        private List<TrackVO> tracks;
    }

    @Data
    public static class TrackVO {
        private Date trackTime;
        private String trackArea;
        private String trackEvent;
    }
}
