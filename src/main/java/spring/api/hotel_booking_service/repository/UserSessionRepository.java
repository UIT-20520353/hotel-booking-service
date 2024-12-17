package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.UserSession;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}
