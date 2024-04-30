package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer>, JpaSpecificationExecutor<Ward> {

    @Query("SELECT w FROM Ward w WHERE w.district.id = :districtId")
    List<Ward> getWardByDistrictId(@Param("districtId") Integer districtId);

}
