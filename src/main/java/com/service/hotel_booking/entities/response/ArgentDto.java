package com.service.hotel_booking.entities.response;

public record ArgentDto(
        Long id,
        String identityNumber,
        String frontIdentityCard,
        String backIdentityCard,
        String selfieImg
) {
}
