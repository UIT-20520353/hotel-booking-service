package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_attractions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "overview_image", nullable = false)
    private String overviewImage;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(precision = 9, scale = 6, name = "latitude", nullable = false)
    private Double latitude;

    @Column(precision = 9, scale = 6, name = "longitude", nullable = false)
    private Double longitude;

}
