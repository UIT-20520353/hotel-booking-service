package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.HotelImage;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Long> {
}
