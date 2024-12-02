package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {

    List<ProvinceDto> getAllProvinces();
    void getProvinceByCode(String code);

}
