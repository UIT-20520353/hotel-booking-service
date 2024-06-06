package com.service.hotel_booking.entities.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateBookingDto {

    private List<Long> roomIds;

}
