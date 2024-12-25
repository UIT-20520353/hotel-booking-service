package spring.api.hotel_booking_service.dto.profile;

import spring.api.hotel_booking_service.helper.enumeration.CustomerStatus;
import spring.api.hotel_booking_service.helper.enumeration.UserGender;
import spring.api.hotel_booking_service.helper.enumeration.UserRole;

import java.time.Instant;

public record CustomerDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        UserRole role,
        UserGender gender,
        CustomerStatus status,
        Instant dateOfBirth
) {
}
