package com.datu.logistics.track.service;

import com.datu.logistics.track.service.dto.TrackDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@FeignClient(value = "track", path = "track", primary = false)
public interface TrackApplicationService {

    @GetMapping("get")
    TrackDTO searchTrack(@RequestParam("orderId") BigInteger orderId);

    @GetMapping("list/get")
    List<TrackDTO> searchTracks(@RequestParam("orderIds") List<BigInteger> orderIds);
}
