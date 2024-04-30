package com.service.hotel_booking.entities.response;

import java.util.List;

public record DistrictResponse(
        Integer id,
        String districtName,
        String districtType,
        Double lat,
        Double lng,
        List<WardResponse> wards
) {
}
