package com.datu.logistics.track.view;

import com.datu.logistics.comm.security.SkipAuthentication;
import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TracksDTO;
import com.datu.logistics.track.view.vo.OrdersTracksVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("view-model")
public class TrackViewModelController {
    private final TrackApplicationService trackApplicationService;

    public TrackViewModelController(TrackApplicationService trackApplicationService) {
        this.trackApplicationService = trackApplicationService;
    }

    @SkipAuthentication
    @GetMapping("orders-tracks/search")
    public OrdersTracksVO searchOrdersTracks(@RequestParam("orderNos") List<String> orderNos) {
        List<TracksDTO> tracksDTOS = trackApplicationService.searchMultiTracks(orderNos);
        return new OrdersTracksVO(tracksDTOS);
    }
}
