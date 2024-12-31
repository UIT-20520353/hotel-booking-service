package spring.api.hotel_booking_service.dto.hotel;

import spring.api.hotel_booking_service.helper.enumeration.HotelStatus;

import java.math.BigDecimal;

public record HotelDto(
        Long id,
        String name,
        String address,
        String description,
        BigDecimal latitude,
        BigDecimal longitude,
        HotelStatus status
) {
}
