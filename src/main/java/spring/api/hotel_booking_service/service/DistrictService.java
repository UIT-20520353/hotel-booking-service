package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.DistrictDto;

import java.util.List;

public interface DistrictService {

    List<DistrictDto> getDistrictByProvinceCode(String code);

}
