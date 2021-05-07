package com.datu.logistics.track.repository.impl.dao;

import com.datu.logistics.track.repository.impl.dao.entity.TrackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackDAO extends JpaRepository<TrackEntity, Long> {

    List<TrackEntity> findAllByOrderId(Long orderId);
}
