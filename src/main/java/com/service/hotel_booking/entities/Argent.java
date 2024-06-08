package com.service.hotel_booking.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_argents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Argent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "front_identity_card")
    private String frontIdentityCard;

    @Column(name = "back_identity_card")
    private String backIdentityCard;

    @Column(name = "selfie_img")
    private String selfieImg;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "argent", fetch = FetchType.LAZY)
    private List<Bank> banks;

}
