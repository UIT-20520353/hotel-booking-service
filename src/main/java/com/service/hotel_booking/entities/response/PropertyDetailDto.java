package com.service.hotel_booking.entities.response;

import com.service.hotel_booking.enumerations.PropertyStatus;

import java.util.List;

public record PropertyDetailDto(
        Long id,
        String name,
        String description,
        String address,
        double longitude,
        double latitude,
        Integer depositPercent,
        PropertyStatus status,
        List<PropertyImageDto> images,
        List<AmenityWithoutTypeDto> amenities,
        PropertyProvinceDto province,
        UserProfileResponse argent,
        List<RoomDto> rooms
) {
}
