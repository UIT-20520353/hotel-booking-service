package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.entities.response.booking.BookingDetailDto;
import com.service.hotel_booking.services.BookingService;
import com.service.hotel_booking.services.criteria.BookingCriteria;
import com.service.hotel_booking.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BookingController {

    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDetailDto> createBooking(@RequestBody @Valid CreateBookingDto body) {
        return ResponseEntity.ok(bookingService.createBooking(body));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookingDetailDto>> getBooking(BookingCriteria criteria,
                                                             @ParameterObject @PageableDefault Pageable pageable) {
        final Page<BookingDetailDto> page = bookingService.getAllBookings(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
