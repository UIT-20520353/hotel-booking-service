package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_districts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "district_name", nullable = false)
    private String districtName;

    @Column(name = "district_type", nullable = false)
    private String districtType;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<Ward> wards;

}
