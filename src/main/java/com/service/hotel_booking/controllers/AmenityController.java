package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.CreateAmenityDtoRequest;
import com.service.hotel_booking.entities.response.AmenityDto;
import com.service.hotel_booking.services.AmenityService;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
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
@Tag(name = "Amenity Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping(value ="/api/amenities")
public class AmenityController {

    AmenityService amenityService;

    @GetMapping
    public ResponseEntity<List<AmenityDto>> getAllAmenities(AmenityCriteria criteria, @ParameterObject @PageableDefault Pageable pageable) {
        final Page<AmenityDto> page = amenityService.getAllAmenities(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping
    public ResponseEntity<Void> createAmenity(@Valid @RequestBody CreateAmenityDtoRequest body) {
        amenityService.createAmenity(body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAmenity(@PathVariable Long id,
                                                   @Valid @RequestBody CreateAmenityDtoRequest body) {
        amenityService.updateAmenity(id, body);
        return ResponseEntity.noContent().build();
    }

}
