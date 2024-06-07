package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class CreateBookingDto {

    @NotNull(message = ROOM_IDS_REQUIRED_ERROR)
    private List<Long> roomIds;

    @NotBlank(message = BOOKING_START_DATE_REQUIRED_ERROR)
    private Instant startDate;

    @NotBlank(message = BOOKING_END_DATE_REQUIRED_ERROR)
    private Instant endDate;

    @NotNull(message = BOOKING_PROPERTY_ID_REQUIRED_ERROR)
    private Long propertyId;

}
