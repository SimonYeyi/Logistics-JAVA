package com.datu.logistics.track.view;

import com.datu.logistics.track.service.TrackApplicationService;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.view.vo.TrackVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigInteger;

@RestController
public class TrackViewModelController {

    @Resource
    private TrackApplicationService trackApplicationService;

    @GetMapping("view-model")
    public TrackVO gettrackViewModel(@RequestParam("trackId") BigInteger trackId) {
        TrackDTO trackDTO = trackApplicationService.searchTrack(trackId);
        return new TrackVO(trackDTO);
    }
}
