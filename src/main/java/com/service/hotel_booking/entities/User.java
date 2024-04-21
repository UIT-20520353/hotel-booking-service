package com.service.hotel_booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.hotel_booking.enumerations.EUserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserSession> sessions;

    public void setNewSession(UserSession session) {
        session.setUser(this);
        this.sessions.clear();
        this.sessions.add(session);
    }


}