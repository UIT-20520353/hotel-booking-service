package com.service.hotel_booking.entities.response;

import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.enumerations.UserStatus;

public record UserWithoutPassword(
        Integer id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        UserRole role,
        UserStatus status,
        ArgentResponse argent
) {
}
