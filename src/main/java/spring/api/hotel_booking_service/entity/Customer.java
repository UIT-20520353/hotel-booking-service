package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.api.hotel_booking_service.helper.enumeration.CustomerStatus;
import spring.api.hotel_booking_service.helper.enumeration.UserGender;

import java.time.Instant;

@Entity
@Table(name = "t_customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}
