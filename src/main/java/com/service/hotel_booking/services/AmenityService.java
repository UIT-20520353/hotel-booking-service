package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.CreateAmenityDtoRequest;
import com.service.hotel_booking.entities.response.AmenityResponseDto;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AmenityService {

    Page<AmenityResponseDto> getAllAmenities(AmenityCriteria criteria, Pageable pageable);
    void createAmenity(CreateAmenityDtoRequest body);
    void deleteAmenity(Long id);
    void updateAmenity(Long id, CreateAmenityDtoRequest body);

}
