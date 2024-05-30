package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_property_amenities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_property_id", referencedColumnName = "id", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity amenity;

}
