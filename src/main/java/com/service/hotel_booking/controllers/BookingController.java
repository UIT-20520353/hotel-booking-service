package com.service.hotel_booking.controllers;

import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.services.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ResponseEntity<Void> createBooking(@RequestBody @Valid CreateBookingDto body) {
        bookingService.createBooking(SecurityUtils.getCurrentUserId(), body);
        return ResponseEntity.ok().build();
    }

}
