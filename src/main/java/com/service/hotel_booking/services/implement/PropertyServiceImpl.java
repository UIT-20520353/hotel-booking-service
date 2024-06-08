package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.*;
import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.enumerations.*;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.PropertyMapper;
import com.service.hotel_booking.repositories.*;
import com.service.hotel_booking.services.*;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import com.service.hotel_booking.services.criteria.PropertyCriteria;
import com.service.hotel_booking.services.query.builder.AmenityTypeFilterBuilder;
import com.service.hotel_booking.services.query.builder.BooleanFilterBuilder;
import com.service.hotel_booking.services.query.builder.LongFilterBuilder;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
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
    AmenityService amenityService;
    PropertyAmenityRepository propertyAmenityRepository;
    ResourceService resourceService;
    PropertyImageRepository propertyImageRepository;

    @Override
    @Transactional
    public void createProperty(PropertyRequestDto body) {
        User user = userService.getUserById(body.getArgentId());
        if (!user.getRole().equals(UserRole.ARGENT) || !user.getStatus().equals(UserStatus.ACTIVE)) {
            throw new BadRequestException(USER_NOT_ALLOWED_TO_CREATE_PROPERTY);
        }

        Province province = locationService.getProvinceEntityById(body.getProvinceId());
        District district = districtRepository.findByProvinceIdAndId(province.getId(), body.getDistrictId())
                .orElseThrow(() -> new BadRequestException(DISTRICT_NOT_IN_PROVINCE_ERROR));
        Ward ward = wardRepository.findByDistrictIdAndId(district.getId(), body.getWardId())
                                  .orElseThrow(() -> new BadRequestException(WARD_NOT_IN_DISTRICT_ERROR));

        List<Pair<byte[], String>> imgDataList = body.getImgList().stream()
                                                     .map(img -> {
                                                         byte[] bytes = FileUtils.checkFile(img);
                                                         return new Pair<>(bytes, img.getContentType());
                                                     })
                                                     .toList();

        Property property = Property
                .builder()
                .name(body.getName())
                .address(body.getAddress())
                .description(body.getDescription())
                .latitude(body.getLatitude())
                .longitude(body.getLongitude())
                .province(province)
                .district(district)
                .ward(ward)
                .argent(user)
                .status(PropertyStatus.AVAILABLE)
                .type(body.getType())
                .depositPercent(body.getDepositPercent())
                .build();
        propertyRepository.save(property);

        imgDataList.forEach(item -> {
            String url = resourceService.uploadPropertyImage(item.a, item.b);
            propertyImageRepository.save(PropertyImage.builder()
                                                      .url(url)
                                                      .property(property)
                                                      .build());
        });

        AmenityCriteria criteria = AmenityCriteria
                .builder()
                .id(LongFilterBuilder.builder().in(body.getAmenityIds()).build())
                .type(AmenityTypeFilterBuilder.builder().equals(AmenityType.PROPERTY).build())
                .isDeleted(BooleanFilterBuilder.builder().equals(false).build())
                .build();
        List<Amenity> amenities = amenityService.getListAmenityEntity(criteria);
        amenities.forEach(amenity -> {
            propertyAmenityRepository.save(PropertyAmenity.builder()
                                                          .property(property)
                                                          .amenity(amenity)
                                                          .build());
        });
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

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        Property property = this.getPropertyEntityById(id);
        property.getRooms().forEach(room -> {
            room.setStatus(RoomStatus.DELETED);
        });
        property.setStatus(PropertyStatus.DELETED);

    }

    @Override
    public Property getPropertyEntityById(Long id) {
        return propertyRepository.findById(id)
                                 .orElseThrow(() -> new BadRequestException(PROPERTY_NOT_EXIST));
    }

    private Specification<Property> createSpecification(PropertyCriteria criteria) {
        Specification<Property> specification = Specification.where(null);

        if (criteria != null) {
            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildSpecification(criteria.getName(), root -> root.get(Property_.name)));
            }

            if (Objects.nonNull(criteria.getStatus())) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Property_.status));
            }
        }

        return specification;
    }



}
