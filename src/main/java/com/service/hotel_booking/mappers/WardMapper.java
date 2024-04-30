package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.response.WardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WardMapper {

    public WardResponse toWardResponse(com.service.hotel_booking.entities.Ward ward) {
        return new WardResponse(ward.getId(), ward.getWardName(), ward.getWardType());
    }

}
