package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.CreateHotelServiceDtoRequest;
import com.service.hotel_booking.entities.response.HotelServiceResponseDto;
import com.service.hotel_booking.services.HotelServiceService;
import com.service.hotel_booking.services.criteria.HotelServiceCriteria;
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
@Tag(name = "Hotel Service Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value ="/api/hotel-services")
public class HotelServiceController {

    HotelServiceService hotelServiceService;

    @GetMapping
    public ResponseEntity<List<HotelServiceResponseDto>> getAllHotelServices(HotelServiceCriteria criteria, @ParameterObject @PageableDefault Pageable pageable) {
        final Page<HotelServiceResponseDto> page = hotelServiceService.getAllHotelServices(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping
    public ResponseEntity<Void> createHotelService(@Valid @RequestBody CreateHotelServiceDtoRequest body) {
        hotelServiceService.createHotelService(body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelService(@PathVariable Long id) {
        hotelServiceService.deleteHotelService(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateHotelService(@PathVariable Long id,
                                                   @Valid @RequestBody CreateHotelServiceDtoRequest body) {
        hotelServiceService.updateHotelService(id, body);
        return ResponseEntity.noContent().build();
    }

}
