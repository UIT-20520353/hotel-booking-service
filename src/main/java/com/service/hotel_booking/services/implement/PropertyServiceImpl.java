package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.*;
import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.PropertyMapper;
import com.service.hotel_booking.repositories.*;
import com.service.hotel_booking.services.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl implements PropertyService {

    UserRepository userRepository;
    WardRepository wardRepository;
    DistrictRepository districtRepository;
    ProvinceRepository provinceRepository;
    PropertyRepository propertyRepository;
    AmenityRepository amenityRepository;
    PropertyMapper propertyMapper;

    @Override
    @Transactional
    public void createProperty(PropertyRequestDto body) {
        User user = userRepository.findById(body.getArgentId())
                                  .orElseThrow(() -> new BadRequestException(USER_NOT_EXIST));
        Province province = provinceRepository.findById(body.getProvinceId())
                .orElseThrow(() -> new BadRequestException(PROVINCE_NOT_EXIST));
        District district = districtRepository.findByProvinceIdAndId(province.getId(), body.getDistrictId())
                .orElseThrow(() -> new BadRequestException(DISTRICT_NOT_IN_PROVINCE_ERROR));
        Ward ward = wardRepository.findByDistrictIdAndId(district.getId(), body.getWardId())
                                  .orElseThrow(() -> new BadRequestException(WARD_NOT_IN_DISTRICT_ERROR));


//        propertyRepository.save(Property
//                                    .builder()
//                                    .name(body.getName())
//                                    .address(body.getAddress())
//                                    .description(body.getDescription())
//                                    .price(body.getPrice())
//                                    .province(province)
//                                    .district(district)
//                                    .ward(ward)
//                                    .argent(user)
//                                    .status(PropertyStatus.AVAILABLE)
//                                    .build());

    }

    @Override
    public PropertyDetailDto getPropertyDetail(Long id) {
        Property property = propertyRepository.findById(id)
                                              .orElseThrow(() -> new BadRequestException(PROPERTY_NOT_EXIST));

        List<Amenity> amenities = amenityRepository.findByIdIn(property.getAmenities().stream().map(amenity -> amenity.getAmenity().getId()).toList());

        return propertyMapper.toPropertyDetail(property, amenities);
    }

}
