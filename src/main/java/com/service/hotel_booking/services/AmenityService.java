package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.CreateAmenityDto;
import com.service.hotel_booking.entities.response.AmenityDto;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AmenityService {

    Page<AmenityDto> getAllAmenities(AmenityCriteria criteria, Pageable pageable);
    void createAmenity(CreateAmenityDto body);
    void deleteAmenity(Long id);
    void updateAmenity(Long id, CreateAmenityDto body);

}
