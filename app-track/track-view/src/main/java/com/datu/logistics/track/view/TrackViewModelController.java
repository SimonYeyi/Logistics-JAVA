package com.datu.logistics.track.view;

import com.datu.logistics.order.service.OrderApplicationService;
import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import com.datu.logistics.track.view.vo.OrdersTracksVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TrackViewModelController {
    private final TrackApplicationService trackApplicationService;

    public TrackViewModelController(TrackApplicationService trackApplicationService) {
        this.trackApplicationService = trackApplicationService;
    }

    @GetMapping("view-model/orders-tracks")
    public OrdersTracksVO getOrdersTracksViewModel(@RequestParam("orderNos") List<String> orderNos) {
        List<TracksDTO> tracksDTOS = trackApplicationService.searchMultiTracks(orderNos);
        return new OrdersTracksVO(tracksDTOS);
    }
}
