package com.service.hotel_booking.entities.response;

import com.service.hotel_booking.enumerations.EUserRole;

public record UserWithoutPassword(
        Integer id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        EUserRole role
) {
}
