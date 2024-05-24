package com.service.hotel_booking.entities;

import com.service.hotel_booking.enumerations.PropertyStatus;
import com.service.hotel_booking.enumerations.PropertyType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_properties")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

}
