package com.service.hotel_booking.entities.response;

public record ArgentResponse(
        Integer id,
        String identityNumber,
        String frontIdentityCard,
        String backIdentityCard,
        String selfieImg
) {
}
