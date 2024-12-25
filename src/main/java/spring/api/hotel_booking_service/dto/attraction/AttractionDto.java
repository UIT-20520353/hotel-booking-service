package spring.api.hotel_booking_service.dto.attraction;

import java.math.BigDecimal;

public record AttractionDto(
        Long id,
        String name,
        String description,
        String address,
        String overviewImage,
        String summary,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
