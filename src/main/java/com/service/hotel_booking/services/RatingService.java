package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.response.RatingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    Page<RatingDto> getRatingsByPropertyId(Long propertyId, Pageable pageable);

}
