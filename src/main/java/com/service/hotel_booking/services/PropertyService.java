package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;

public interface PropertyService {
    void createProperty(PropertyRequestDto body);
    PropertyDetailDto getPropertyDetail(Long id);
}
