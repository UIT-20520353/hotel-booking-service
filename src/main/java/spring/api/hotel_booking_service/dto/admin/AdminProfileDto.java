package spring.api.hotel_booking_service.dto.admin;

import spring.api.hotel_booking_service.helper.enumeration.UserRole;

public record AdminProfileDto(
        Long id,
        String email,
        UserRole role,
        String avatar
) {
}
