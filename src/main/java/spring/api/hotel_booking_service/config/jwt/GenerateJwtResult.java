package spring.api.hotel_booking_service.config.jwt;

import java.time.Instant;

public record GenerateJwtResult(
        String tokenId,
        String accessToken,
        String refreshToken,
        Instant expiredDate
) {
}
