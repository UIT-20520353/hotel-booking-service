package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.PropertyAmenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyAmenityRepository extends JpaRepository<PropertyAmenity, Long> {
}
