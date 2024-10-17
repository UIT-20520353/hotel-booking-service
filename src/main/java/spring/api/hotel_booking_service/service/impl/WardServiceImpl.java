package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.WardDto;
import spring.api.hotel_booking_service.helper.mapper.WardMapper;
import spring.api.hotel_booking_service.repository.WardRepository;
import spring.api.hotel_booking_service.service.WardService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WardServiceImpl implements WardService {

    WardRepository wardRepository;
    WardMapper wardMapper;

    @Override
    public List<WardDto> getWardsByDistrictCode(String districtCode) {
        return wardRepository.findByDistrictCode(districtCode)
                             .stream()
                             .map(wardMapper::toWardDto)
                             .toList();
    }

}
