package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.response.RatingDto;
import com.service.hotel_booking.services.RatingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingServiceImpl implements RatingService {

    @Override
    public Page<RatingDto> getRatingsByPropertyId(Long propertyId, Pageable pageable) {
        return null;
    }

}
