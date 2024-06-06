package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.PropertyRequestDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.services.PropertyService;
import com.service.hotel_booking.services.criteria.PropertyCriteria;
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
@RequestMapping("/api/properties")
@Tag(name = "Property Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PropertyController {

PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDetailDto>> getPropertyList(PropertyCriteria criteria,
                                                @ParameterObject @PageableDefault Pageable pageable) {
        final Page<PropertyDetailDto> page = propertyService.getAllProperties(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<PropertyDetailDto> createProperty(@ModelAttribute @Valid PropertyRequestDto body) {
        propertyService.createProperty(body);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDetailDto> getPropertyDetail(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyDetail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

}

