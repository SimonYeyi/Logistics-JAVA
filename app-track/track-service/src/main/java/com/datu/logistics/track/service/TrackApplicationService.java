package com.datu.logistics.track.service;

import com.datu.logistics.track.service.dto.TrackDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@FeignClient(value = "track", path = "track", primary = false)
public interface TrackApplicationService {

    @GetMapping("{id}/get")
    TrackDTO searchTrack(@PathVariable("id") BigInteger id);
}
