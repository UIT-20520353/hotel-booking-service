package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.ProvinceDto;
import spring.api.hotel_booking_service.helper.exception.NotFoundException;
import spring.api.hotel_booking_service.helper.mapper.ProvinceMapper;
import spring.api.hotel_booking_service.repository.ProvinceRepository;
import spring.api.hotel_booking_service.service.ProvinceService;

import java.util.List;

import static spring.api.hotel_booking_service.helper.constant.Message.PROVINCE_NOT_FOUND;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    ProvinceRepository provinceRepository;
    ProvinceMapper provinceMapper;

    @Override
    public List<ProvinceDto> getAllProvinces() {
        return provinceRepository.findAll().stream().map(provinceMapper::toProvinceDto).toList();
    }

    @Override
    public void getProvinceByCode(String code) {
        provinceRepository.findByCode(code).orElseThrow(() -> new NotFoundException(PROVINCE_NOT_FOUND));
    }

}
