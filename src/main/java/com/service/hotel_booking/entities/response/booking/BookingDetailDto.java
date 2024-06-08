package com.service.hotel_booking.entities.response.booking;

import com.service.hotel_booking.entities.response.BankDetailDto;
import com.service.hotel_booking.enumerations.BookingStatus;

import java.time.Instant;
import java.util.List;

public record BookingDetailDto(
        Long id,
        Instant startDate,
        Instant endDate,
        Long totalPrice,
        Long depositMoney,
        BookingStatus status,
        Instant createAt,
        List<BankDetailDto> banks,
        BookingPropertyDto property
) {
}
