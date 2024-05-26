package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.services.PropertyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
@Tag(name = "Property Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PropertyController {

    PropertyService propertyService;

    @GetMapping
    public ResponseEntity<Void> getPropertyList(@ParameterObject @PageableDefault Pageable pageable) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Void> uploadImageList(@ModelAttribute @Valid PropertyRequestDto body) {
        propertyService.createProperty(body);
        return ResponseEntity.noContent().build();
    }

}
