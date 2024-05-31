package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.District;
import com.service.hotel_booking.entities.Ward;
import com.service.hotel_booking.entities.response.DistrictResponse;
import com.service.hotel_booking.entities.response.PropertyDistrictDto;
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

    public PropertyDistrictDto toPropertyDistrictDto(District district, Ward ward) {
        return new PropertyDistrictDto(
                district.getId(),
                district.getDistrictName(),
                district.getDistrictType(),
                district.getLat(),
                district.getLng(),
                wardMapper.toWardResponse(ward)
        );
    }

}
