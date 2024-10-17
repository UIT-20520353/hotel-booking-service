package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.hotel_booking_service.entity.AdministrativeRegion;
import spring.api.hotel_booking_service.entity.AdministrativeUnit;
import spring.api.hotel_booking_service.service.AdministrativeRegionService;
import spring.api.hotel_booking_service.service.AdministrativeUnitService;

import java.util.List;

@RestController
@RequestMapping("/api/common")
@Tag(name = "Administrative Region - Unit", description = "Administrative regions, units API")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdministrativeController {

    AdministrativeRegionService administrativeRegionService;
    AdministrativeUnitService administrativeUnitService;

    @GetMapping("/administrative-regions")
    public ResponseEntity<List<AdministrativeRegion>> getAllRegions() {
        return ResponseEntity.ok(administrativeRegionService.getAllRegions());
    }

    @GetMapping("/administrative-units")
    public ResponseEntity<List<AdministrativeUnit>> getAllAdministrativeUnits() {
        return ResponseEntity.ok(administrativeUnitService.getAllAdministrativeUnits());
    }

}
