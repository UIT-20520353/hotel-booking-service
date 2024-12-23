package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.hotel_booking_service.dto.attraction.CreateAttractionDto;
import spring.api.hotel_booking_service.service.AttractionService;

@Tag(name = "Attractions resources")
@RequestMapping("/api/admin/attractions")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminAttractionController {

    AttractionService attractionService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> createAttraction(@Valid @ModelAttribute CreateAttractionDto requestBody) {
        attractionService.createAttraction(requestBody);
        return ResponseEntity.noContent().build();
    }

}
