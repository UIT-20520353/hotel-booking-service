package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "province_name", nullable = false)
    private String provinceName;

    @Column(name = "province_type", nullable = false)
    private String provinceType;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<District> districts;

}
