package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.ProvinceDto;
import spring.api.hotel_booking_service.helper.mapper.ProvinceMapper;
import spring.api.hotel_booking_service.repository.ProvinceRepository;
import spring.api.hotel_booking_service.service.ProvinceService;

import java.util.List;

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

}
