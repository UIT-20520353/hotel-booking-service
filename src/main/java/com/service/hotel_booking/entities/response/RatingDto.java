package com.service.hotel_booking.entities.response;

import java.time.Instant;

public record RatingDto(
        Long id,
        String comment,
        Integer star,
        Instant createdDate,
        UserDetailDto user
) {
}
