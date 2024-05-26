package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_property_images")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_property_id", referencedColumnName = "id", nullable = false)
    private Property property;

}
