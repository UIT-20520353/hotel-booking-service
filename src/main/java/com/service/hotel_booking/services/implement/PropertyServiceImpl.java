package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.District;
import com.service.hotel_booking.entities.Province;
import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.Ward;
import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.repositories.DistrictRepository;
import com.service.hotel_booking.repositories.ProvinceRepository;
import com.service.hotel_booking.repositories.UserRepository;
import com.service.hotel_booking.repositories.WardRepository;
import com.service.hotel_booking.services.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl implements PropertyService {

    UserRepository userRepository;
    WardRepository wardRepository;
    DistrictRepository districtRepository;
    ProvinceRepository provinceRepository;

    @Override
    @Transactional
    public void createProperty(PropertyRequestDto body) {
        User user = userRepository.findById(body.getArgentId())
                                  .orElseThrow(() -> new BadRequestException(USER_NOT_EXIST));
        Ward ward = wardRepository.findById(body.getWardId())
                                  .orElseThrow(() -> new BadRequestException(WARD_NOT_EXIST));
        District district = districtRepository.findById(body.getDistrictId())
                                              .orElseThrow(() -> new BadRequestException(DISTRICT_NOT_EXIST));
        Province province = provinceRepository.findById(body.getProvinceId())
                                              .orElseThrow(() -> new BadRequestException(PROVINCE_NOT_EXIST));

    }

}
