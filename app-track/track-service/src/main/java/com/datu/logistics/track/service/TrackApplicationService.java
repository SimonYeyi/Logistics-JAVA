package com.datu.logistics.track.service;

import com.datu.logistics.track.service.command.TrackCreateCommand;
import com.datu.logistics.track.service.dto.TrackDTO;
import com.datu.logistics.track.service.dto.TracksDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@FeignClient(value = "track", path = "track", primary = false)
public interface TrackApplicationService {

    @PostMapping("add")
    TrackDTO addTrack(@RequestBody TrackCreateCommand trackCreateCommand);

    @GetMapping("list/search")
    TracksDTO searchTracks(@RequestParam("orderNo") String orderNo);

    @GetMapping("list/multi/search")
    List<TracksDTO> searchMultiTracks(@RequestParam("orderNos") List<String> orderNos);

    @GetMapping("list/get")
    TracksDTO getTracks(@RequestParam("orderNo") String orderNo);
}
