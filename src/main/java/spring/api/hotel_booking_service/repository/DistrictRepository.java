package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.District;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {

    List<District> findByProvinceCode(String code);

}
