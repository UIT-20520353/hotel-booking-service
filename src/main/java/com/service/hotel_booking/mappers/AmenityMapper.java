package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.response.AmenityResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public AmenityResponseDto toAmenityDto(Amenity amenity) {
        return new AmenityResponseDto(
                amenity.getId(),
                amenity.getName()
        );
    }

}
