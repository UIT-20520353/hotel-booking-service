package com.service.hotel_booking.config.jwt;

import java.time.Instant;

public record GenerateJwtResult(
        String tokenId,
        String accessToken,
        String refreshToken,
        Instant expiredDate
) {
}
