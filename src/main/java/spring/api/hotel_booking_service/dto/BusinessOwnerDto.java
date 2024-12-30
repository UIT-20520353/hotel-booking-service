package spring.api.hotel_booking_service.dto;

import spring.api.hotel_booking_service.helper.enumeration.BusinessOwnerStatus;

public record BusinessOwnerDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String businessName,
        BusinessOwnerStatus status,
        String address,
        String businessId,
        String frontIdentityCard,
        String backIdentityCard,
        String selfieImage
) {
}
