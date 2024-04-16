package com.service.hotel_booking.entities.response;

public record UserWithoutPassword(
        Integer id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
