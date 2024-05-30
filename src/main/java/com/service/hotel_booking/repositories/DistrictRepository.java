package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>, JpaSpecificationExecutor<District> {

    List<District> getDistrictsByProvinceId(Integer id);
    Optional<District> findByProvinceIdAndId(Integer province_id, Integer id);

}
