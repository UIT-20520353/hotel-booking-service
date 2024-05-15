package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelServiceRepository extends JpaRepository<HotelService, Long>, JpaSpecificationExecutor<HotelService> {
}
