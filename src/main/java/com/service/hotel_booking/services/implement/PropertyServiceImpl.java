package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.*;
import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.PropertyMapper;
import com.service.hotel_booking.repositories.DistrictRepository;
import com.service.hotel_booking.repositories.PropertyRepository;
import com.service.hotel_booking.repositories.WardRepository;
import com.service.hotel_booking.services.LocationService;
import com.service.hotel_booking.services.PropertyService;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.criteria.PropertyCriteria;
import com.service.hotel_booking.services.query.QueryService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyServiceImpl extends QueryService<Property> implements PropertyService {

    UserService userService;
    WardRepository wardRepository;
    DistrictRepository districtRepository;
    LocationService locationService;
    PropertyRepository propertyRepository;
    PropertyMapper propertyMapper;

    @Override
    @Transactional
    public void createProperty(PropertyRequestDto body) {
        User user = userService.getUserById(body.getArgentId());
        Province province = locationService.getProvinceEntityById(body.getProvinceId());
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

        return propertyMapper.toPropertyDetail(property);
    }

    @Override
    public Page<PropertyDetailDto> getAllProperties(PropertyCriteria criteria, Pageable pageable) {
        Specification<Property> specification = createSpecification(criteria);
        return propertyRepository.findAll(specification, pageable).map(propertyMapper::toPropertyDetail);
    }

    private Specification<Property> createSpecification(PropertyCriteria criteria) {
        Specification<Property> specification = Specification.where(null);

        if (criteria != null) {
            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildSpecification(criteria.getName(), root -> root.get(Property_.name)));
            }
        }

        return specification;
    }

}
