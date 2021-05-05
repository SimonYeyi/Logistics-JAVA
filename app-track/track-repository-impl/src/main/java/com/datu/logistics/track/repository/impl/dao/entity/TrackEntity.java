package com.datu.logistics.track.repository.impl.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "track")
@Table(name = "track",schema = "logistics_track")
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String area;
    private String event;
    private Date time;
    private String orderNo;
}
