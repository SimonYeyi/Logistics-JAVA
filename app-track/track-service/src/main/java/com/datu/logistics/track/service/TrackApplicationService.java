package com.datu.logistics.track.service;

import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@FeignClient(value = "track", path = "track", primary = false)
public interface TrackApplicationService {

    @PostMapping("create")
    TrackDTO createTrack(@RequestParam("orderId") Long orderId, @RequestBody TrackDTO trackDTO);

    @GetMapping("list/get")
    TracksDTO searchTracks(@RequestParam("orderNo") String orderNo);

    @GetMapping("list/multi/get")
    List<TracksDTO> searchMultiTracks(@RequestParam("orderNos") List<String> orderNos);
}
