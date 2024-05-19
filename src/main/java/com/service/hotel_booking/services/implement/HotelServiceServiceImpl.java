package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.HotelService;
import com.service.hotel_booking.entities.HotelService_;
import com.service.hotel_booking.entities.request.CreateHotelServiceDtoRequest;
import com.service.hotel_booking.entities.response.HotelServiceResponseDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.HotelServiceMapper;
import com.service.hotel_booking.repositories.HotelServiceRepository;
import com.service.hotel_booking.services.HotelServiceService;
import com.service.hotel_booking.services.criteria.HotelServiceCriteria;
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
public class HotelServiceServiceImpl extends QueryService<HotelService> implements HotelServiceService {

    HotelServiceRepository hotelServiceRepository;
    HotelServiceMapper hotelServiceMapper;

    @Override
    public Page<HotelServiceResponseDto> getAllHotelServices(HotelServiceCriteria criteria, Pageable pageable) {
        Specification<HotelService> specification = createSpecification(criteria);
        return hotelServiceRepository.findAll(specification, pageable).map(hotelServiceMapper::toHotelServiceDto);
    }

    @Override
    @Transactional
    public void createHotelService(CreateHotelServiceDtoRequest body) {
        hotelServiceRepository.save(HotelService
                                        .builder()
                                        .name(body.getName())
                                        .isDeleted(false)
                                        .build());
    }

    @Override
    @Transactional
    public void deleteHotelService(Long id) {
        HotelService hotelService = hotelServiceRepository
                                        .findById(id)
                                        .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        hotelService.setIsDeleted(true);
    }

    @Override
    @Transactional
    public void updateHotelService(Long id, CreateHotelServiceDtoRequest body) {
        HotelService hotelService = hotelServiceRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        hotelService.setName(body.getName());
    }

    private Specification<HotelService> createSpecification(HotelServiceCriteria criteria) {
        Specification<HotelService> specification = Specification.where(null);

        BooleanFilter booleanFilter = new BooleanFilter();
        booleanFilter.setEquals(false);

        specification = specification.and(buildSpecification(booleanFilter,
                                                             root -> root.get(HotelService_.isDeleted)));

        if (criteria != null) {
            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildSpecification(criteria.getName(),
                                                                     root -> root.get(HotelService_.name)));
            }

            if (Objects.nonNull(criteria.getId())) {
                specification = specification.and(buildSpecification(criteria.getId(),
                                                                     root -> root.get(HotelService_.id)));
            }
        }

        return specification;
    }

}
