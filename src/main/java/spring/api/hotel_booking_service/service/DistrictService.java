package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.entity.DistrictDto;

import java.util.List;

public interface DistrictService {

    List<DistrictDto> getDistrictByProvinceCode(String code);

}
