package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.hotel_booking_service.dto.hotel.CreateAmenityDto;
import spring.api.hotel_booking_service.dto.hotel.CreateHotelDto;
import spring.api.hotel_booking_service.service.HotelService;

@Tag(name = "Hotel resources")
@RequestMapping("/api/business/hotels")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HotelController {

    HotelService hotelService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Void> createHotel(@Valid @ModelAttribute CreateHotelDto body) {
        hotelService.createHotel(body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/amenities")
    public ResponseEntity<Void> addAmenity(@PathVariable Long id, @RequestBody CreateAmenityDto body) {
        hotelService.addAmenity(id, body);
        return ResponseEntity.noContent().build();
    }

}
