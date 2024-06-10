package com.service.hotel_booking.controllers.argent;

import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.services.PropertyService;
import com.service.hotel_booking.services.criteria.PropertyCriteria;
import com.service.hotel_booking.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/argent/properties")
@Tag(name = "Argent Property Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArgentPropertyController {

    PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDetailDto>> getPropertyList(PropertyCriteria criteria,
                                                                   @ParameterObject @PageableDefault Pageable pageable) {
        final Page<PropertyDetailDto> page = propertyService.getAllPropertiesWithArgentId(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

}
