package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_room_amenities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_amenity_id", referencedColumnName = "id", nullable = false)
    private Amenity amenity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_room_id", referencedColumnName = "id", nullable = false)
    private Room room;

}
