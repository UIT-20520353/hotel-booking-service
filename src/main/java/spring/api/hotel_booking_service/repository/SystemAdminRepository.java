package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.SystemAdmin;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {
}
