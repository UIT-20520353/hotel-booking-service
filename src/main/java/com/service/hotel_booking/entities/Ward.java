package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ward_name", nullable = false)
    private String wardName;

    @Column(name = "ward_type", nullable = false)
    private String wardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_district_id", nullable = false)
    private District district;

}
