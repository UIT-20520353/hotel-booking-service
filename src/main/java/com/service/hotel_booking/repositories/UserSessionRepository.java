package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    boolean existsByTokenId(String tokenId);

    Optional<UserSession> findByTokenId(String tokenId);

}
