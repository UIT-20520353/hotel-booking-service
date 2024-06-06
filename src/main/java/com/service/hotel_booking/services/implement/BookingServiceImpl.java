package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {

    @Override
    @Transactional
    public void createBooking(Long userId, CreateBookingDto body) {

    }

}
