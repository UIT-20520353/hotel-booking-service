package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.api.hotel_booking_service.helper.enumeration.HotelStatus;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_hotels")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(precision = 9, scale = 6, name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6, name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private HotelStatus status;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelImage> images;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HotelAmenity> amenities;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private BusinessOwner owner;

}
