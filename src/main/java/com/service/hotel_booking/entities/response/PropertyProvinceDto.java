package com.service.hotel_booking.entities.response;

public record PropertyProvinceDto(
        Integer id,
        String provinceName,
        String provinceType,
        PropertyDistrictDto district
) {
}
