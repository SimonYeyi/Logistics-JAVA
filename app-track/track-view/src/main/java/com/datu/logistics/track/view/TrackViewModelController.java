package com.datu.logistics.track.view;

import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.view.vo.TrackVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrackViewModelController {

    @Resource
    private TrackApplicationService trackApplicationService;

    @GetMapping("view-model")
    public TrackVO getTrackViewModel(@RequestParam("orderId") BigInteger orderId) {
        TrackDTO trackDTO = trackApplicationService.searchTrack(orderId);
        return new TrackVO(trackDTO);
    }

    @GetMapping("view-model/list")
    public List<TrackVO> getTracksViewModel(@RequestParam("orderId") List<BigInteger> orderIds) {
        List<TrackDTO> trackDTOS = trackApplicationService.searchTracks(orderIds);
        return trackDTOS.stream()
                .map(TrackVO::new)
                .collect(Collectors.toList());
    }
}
