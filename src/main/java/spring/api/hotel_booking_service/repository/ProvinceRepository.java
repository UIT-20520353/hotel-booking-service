package spring.api.hotel_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.hotel_booking_service.entity.Province;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

    Optional<Province> findByCode(String code);

}
