package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.District;
import com.service.hotel_booking.entities.response.DistrictResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistrictMapper {

    WardMapper wardMapper;

    public DistrictResponse toDistrictResponse(District district) {
        return new DistrictResponse(
                district.getId(),
                district.getDistrictName(),
                district.getDistrictType(),
                district.getLat(),
                district.getLng(),
                district.getWards().stream().map(wardMapper::toWardResponse).toList()
        );
    }

}
