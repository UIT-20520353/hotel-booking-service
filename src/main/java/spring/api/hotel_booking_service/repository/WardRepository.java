package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.Ward;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {

    List<Ward> findByDistrictCode(String districtCode);

}
