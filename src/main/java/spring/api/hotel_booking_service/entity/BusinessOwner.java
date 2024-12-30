package spring.api.hotel_booking_service.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.api.hotel_booking_service.helper.enumeration.BusinessOwnerStatus;

@Entity
@Table(name = "t_business_owners")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessOwner {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessOwnerStatus status;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "business_id", nullable = false)
    private String businessId;

    @Column(name = "front_identity_card", nullable = false)
    private String frontIdentityCard;

    @Column(name = "back_identity_card", nullable = false)
    private String backIdentityCard;

    @Column(name = "selfie_image", nullable = false)
    private String selfieImage;

}
