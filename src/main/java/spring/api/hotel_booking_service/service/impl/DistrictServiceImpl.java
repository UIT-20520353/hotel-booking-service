package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.DistrictDto;
import spring.api.hotel_booking_service.helper.mapper.DistrictMapper;
import spring.api.hotel_booking_service.repository.DistrictRepository;
import spring.api.hotel_booking_service.service.DistrictService;
import spring.api.hotel_booking_service.service.ProvinceService;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    ProvinceService provinceService;
    DistrictRepository districtRepository;
    DistrictMapper districtMapper;

    @Override
    public List<DistrictDto> getDistrictByProvinceCode(String code) {
        provinceService.getProvinceByCode(code);
        return districtRepository.findByProvinceCode(code)
                                 .stream()
                                 .map(districtMapper::toDistrictDto)
                                 .toList();
    }

}
