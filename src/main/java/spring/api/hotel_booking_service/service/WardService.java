package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.WardDto;

import java.util.List;

public interface WardService {

    List<WardDto> getWardsByDistrictCode(String districtCode);

}
