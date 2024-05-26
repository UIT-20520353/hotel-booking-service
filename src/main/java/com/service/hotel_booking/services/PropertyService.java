package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.PropertyRequestDto;

public interface PropertyService {
    void createProperty(PropertyRequestDto body);
}
