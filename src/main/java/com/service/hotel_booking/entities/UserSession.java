package com.service.hotel_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "t_user_web_sessions")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token_id", nullable = false)
    private String tokenId;

    @Column(name = "expired_date", nullable = false)
    private Instant expiredDate;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    public UserSession(String tokenId, Instant expiredDate) {
        this.tokenId = tokenId;
        this.expiredDate = expiredDate;
    }

}
