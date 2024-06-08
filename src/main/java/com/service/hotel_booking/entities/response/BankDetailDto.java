package com.service.hotel_booking.entities.response;

import java.time.Instant;

public record BankDetailDto(
        Long id,
        String bankName,
        String accountNumber,
        String accountName,
        String qrCode,
        boolean isDefault,
        Instant createAt
) {
}
