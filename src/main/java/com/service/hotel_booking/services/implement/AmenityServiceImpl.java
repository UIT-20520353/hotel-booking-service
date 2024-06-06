package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Amenity_;
import com.service.hotel_booking.entities.request.CreateAmenityDto;
import com.service.hotel_booking.entities.response.AmenityDto;
import com.service.hotel_booking.enumerations.AmenityType;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.AmenityMapper;
import com.service.hotel_booking.repositories.AmenityRepository;
import com.service.hotel_booking.services.AmenityService;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.services.query.filter.BooleanFilter;
import com.service.hotel_booking.utils.EnumUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.AMENITY_NOT_EXIST;
import static com.service.hotel_booking.constant.MessageConstant.AMENITY_TYPE_INVALID_ERROR;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AmenityServiceImpl extends QueryService<Amenity> implements AmenityService {

    AmenityRepository amenityRepository;
    AmenityMapper amenityMapper;

    @Override
    public Page<AmenityDto> getAllAmenities(AmenityCriteria criteria, Pageable pageable) {
        Specification<Amenity> specification = createSpecification(criteria);
        return amenityRepository.findAll(specification, pageable).map(amenityMapper::toAmenityDto);
    }

    @Override
    @Transactional
    public void createAmenity(CreateAmenityDto body) {
        if (!EnumUtils.isInEnum(body.getType().value, AmenityType.class)) {
            throw new BadRequestException(AMENITY_TYPE_INVALID_ERROR);
        }

        amenityRepository.save(Amenity
                                        .builder()
                                        .name(body.getName())
                                        .isDeleted(false)
                                       .type(body.getType())
                                        .build());
    }

    @Override
    @Transactional
    public void deleteAmenity(Long id) {
        Amenity amenity = this.getAmenityEntityById(id);
        amenity.setIsDeleted(true);
    }

    @Override
    @Transactional
    public void updateAmenity(Long id, CreateAmenityDto body) {
        Amenity amenity = this.getAmenityEntityById(id);;
        amenity.setName(body.getName());
        amenity.setType(body.getType());
    }

    @Override
    public Amenity getAmenityEntityById(Long id) {
        return amenityRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(AMENITY_NOT_EXIST));
    }

    @Override
    public List<Amenity> getListAmenityEntity(AmenityCriteria criteria) {
        Specification<Amenity> specification = createSpecification(criteria);
        return amenityRepository.findAll(specification);
    }


    private Specification<Amenity> createSpecification(AmenityCriteria criteria) {
        Specification<Amenity> specification = Specification.where(null);

        BooleanFilter booleanFilter = new BooleanFilter();
        booleanFilter.setEquals(false);

        specification = specification.and(buildSpecification(booleanFilter, Amenity_.isDeleted));

        if (criteria != null) {
            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildSpecification(criteria.getName(), root -> root.get(Amenity_.name)));
            }

            if (Objects.nonNull(criteria.getId())) {
                specification = specification.and(buildSpecification(criteria.getId(), Amenity_.id));
            }

            if (Objects.nonNull(criteria.getType())) {
                specification = specification.and(buildSpecification(criteria.getType(), Amenity_.type));
            }
        }

        return specification;
    }

}
