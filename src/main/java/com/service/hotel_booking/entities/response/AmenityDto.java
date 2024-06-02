package com.service.hotel_booking.entities.response;

import com.service.hotel_booking.enumerations.AmenityType;

public record AmenityDto(
        Long id,
        String name,
        AmenityType type
) {
}
