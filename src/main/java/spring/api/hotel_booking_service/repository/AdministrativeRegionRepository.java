package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.AdministrativeRegion;

@Repository
public interface AdministrativeRegionRepository extends JpaRepository<AdministrativeRegion, Integer> {
}
