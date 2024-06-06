package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.CreateBookingDto;

public interface BookingService {

    void createBooking(Long userId, CreateBookingDto body);

}
