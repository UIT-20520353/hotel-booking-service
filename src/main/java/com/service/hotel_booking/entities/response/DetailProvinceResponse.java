package com.service.hotel_booking.entities.response;

import java.util.List;

public record DetailProvinceResponse(
        Integer id,
        String provinceName,
        String provinceType,
        List<DistrictResponse> districts
) {
}
