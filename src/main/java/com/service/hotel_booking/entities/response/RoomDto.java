package com.service.hotel_booking.entities.response;

import com.service.hotel_booking.enumerations.RoomStatus;

import java.util.List;

public record RoomDto(Long id,
                      String name,
                      Long price,
                      RoomStatus status,
                      List<RoomImageDto> images
) {
}
