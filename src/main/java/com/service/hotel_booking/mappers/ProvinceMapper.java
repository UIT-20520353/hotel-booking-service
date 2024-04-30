package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Province;
import com.service.hotel_booking.entities.response.DetailProvinceResponse;
import com.service.hotel_booking.entities.response.SimpleProvinceResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProvinceMapper {

    DistrictMapper districtMapper;

    public SimpleProvinceResponse toSimpleProvince(Province province) {
        return new SimpleProvinceResponse(
                province.getId(),
                province.getProvinceName(),
                province.getProvinceType()
        );
    }

    public DetailProvinceResponse toDetailProvince(Province province) {
        return new DetailProvinceResponse(
                province.getId(),
                province.getProvinceName(),
                province.getProvinceType(),
                province.getDistricts().stream().map(districtMapper::toDistrictResponse).toList()
        );
    }

}
