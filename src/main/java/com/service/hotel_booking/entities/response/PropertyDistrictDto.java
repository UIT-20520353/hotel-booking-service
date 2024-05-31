package com.service.hotel_booking.entities.response;

public record PropertyDistrictDto(
        Integer id,
        String districtName,
        String districtType,
        Double lat,
        Double lng,
        WardResponse ward
) {
}
