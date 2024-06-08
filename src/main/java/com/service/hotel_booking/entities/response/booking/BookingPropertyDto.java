package com.service.hotel_booking.entities.response.booking;

import com.service.hotel_booking.entities.response.PropertyImageDto;
import com.service.hotel_booking.entities.response.PropertyProvinceDto;
import com.service.hotel_booking.entities.response.RoomDto;
import com.service.hotel_booking.enumerations.PropertyType;

import java.util.List;

public record BookingPropertyDto(
        Long id,
        String name,
        String address,
        String description,
        PropertyProvinceDto province,
        double longitude,
        double latitude,
        List<PropertyImageDto> images,
        PropertyType type,
        List<RoomDto> rooms
) {
}
