package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.api.hotel_booking_service.helper.enumeration.HotelImageType;

@Entity
@Table(name = "t_hotel_images")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    Hotel hotel;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    HotelImageType type;

}
