package com.service.hotel_booking.entities;

import com.service.hotel_booking.enumerations.AmenityType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_amenities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AmenityType type;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}
