package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.Booking;
import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.entities.response.booking.BookingDetailDto;
import com.service.hotel_booking.services.criteria.BookingCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingDetailDto createBooking(CreateBookingDto body);
    void deleteBooking(Long bookingId);
    Booking getBookingEntityById(Long bookingId);
    Page<BookingDetailDto> getAllBookings(BookingCriteria criteria, Pageable pageable);

}
