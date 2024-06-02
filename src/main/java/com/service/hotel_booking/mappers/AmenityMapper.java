package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.response.AmenityDto;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public AmenityDto toAmenityDto(Amenity amenity) {
        return new AmenityDto(
                amenity.getId(),
                amenity.getName(),
                amenity.getType()
        );
    }

}
