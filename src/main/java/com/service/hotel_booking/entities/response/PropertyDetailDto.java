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
        PropertyStatus status,
        List<PropertyImageDto> images,
        List<AmenityDto> amenities,
        PropertyProvinceDto province,
        UserProfileResponse argent,
        List<RoomDto> rooms
) {
}
