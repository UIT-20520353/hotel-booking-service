package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Amenity_;
import com.service.hotel_booking.entities.request.CreateAmenityDtoRequest;
import com.service.hotel_booking.entities.response.AmenityResponseDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.AmenityMapper;
import com.service.hotel_booking.repositories.AmenityRepository;
import com.service.hotel_booking.services.AmenityService;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.services.query.filter.BooleanFilter;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.HOTEL_SERVICE_NOT_EXIST;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmenityServiceImpl extends QueryService<Amenity> implements AmenityService {

    AmenityRepository amenityRepository;
    AmenityMapper amenityMapper;

    @Override
    public Page<AmenityResponseDto> getAllAmenities(AmenityCriteria criteria, Pageable pageable) {
        Specification<Amenity> specification = createSpecification(criteria);
        return amenityRepository.findAll(specification, pageable).map(amenityMapper::toAmenityDto);
    }

    @Override
    @Transactional
    public void createAmenity(CreateAmenityDtoRequest body) {
        amenityRepository.save(Amenity
                                        .builder()
                                        .name(body.getName())
                                        .isDeleted(false)
                                        .build());
    }

    @Override
    @Transactional
    public void deleteAmenity(Long id) {
        Amenity amenity = amenityRepository
                                        .findById(id)
                                        .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        amenity.setIsDeleted(true);
    }

    @Override
    @Transactional
    public void updateAmenity(Long id, CreateAmenityDtoRequest body) {
        Amenity amenity = amenityRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        amenity.setName(body.getName());
    }

    private Specification<Amenity> createSpecification(AmenityCriteria criteria) {
        Specification<Amenity> specification = Specification.where(null);

        BooleanFilter booleanFilter = new BooleanFilter();
        booleanFilter.setEquals(false);

        specification = specification.and(buildSpecification(booleanFilter,
                                                             root -> root.get(Amenity_.isDeleted)));

        if (criteria != null) {
            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildSpecification(criteria.getName(),
                                                                     root -> root.get(Amenity_.name)));
            }

            if (Objects.nonNull(criteria.getId())) {
                specification = specification.and(buildSpecification(criteria.getId(),
                                                                     root -> root.get(Amenity_.id)));
            }
        }

        return specification;
    }

}
