package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.services.criteria.PropertyCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {
    void createProperty(PropertyRequestDto body);
    PropertyDetailDto getPropertyDetail(Long id);
    Page<PropertyDetailDto> getAllProperties(PropertyCriteria criteria, Pageable pageable);
}
