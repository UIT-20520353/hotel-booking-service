package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.HotelService;
import com.service.hotel_booking.entities.response.HotelServiceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HotelServiceMapper {

    public HotelServiceResponseDto toHotelServiceDto(HotelService hotelService) {
        return new HotelServiceResponseDto(
                hotelService.getId(),
                hotelService.getName()
        );
    }

}
