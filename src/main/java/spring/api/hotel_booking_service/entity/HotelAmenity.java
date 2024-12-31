package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_hotel_amenities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelAmenity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    Hotel hotel;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "price", nullable = false)
    private Integer price;

}
