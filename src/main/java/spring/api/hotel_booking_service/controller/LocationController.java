package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.hotel_booking_service.dto.ProvinceDto;
import spring.api.hotel_booking_service.dto.WardDto;
import spring.api.hotel_booking_service.entity.DistrictDto;
import spring.api.hotel_booking_service.service.DistrictService;
import spring.api.hotel_booking_service.service.ProvinceService;
import spring.api.hotel_booking_service.service.WardService;

import java.util.List;

@Tag(name = "Location")
@RequestMapping("/api/common")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LocationController {

    ProvinceService provinceService;
    DistrictService districtService;
    WardService wardService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceDto>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @GetMapping("/districts/{provinceCode}")
    public ResponseEntity<List<DistrictDto>> getDistrictsByProvinceCode(@PathVariable String provinceCode) {
        return ResponseEntity.ok(districtService.getDistrictByProvinceCode(provinceCode));
    }

    @GetMapping("/wards/{districtCode}")
    public ResponseEntity<List<WardDto>> getWardsByDistrictCode(@PathVariable String districtCode) {
        return ResponseEntity.ok(wardService.getWardsByDistrictCode(districtCode));
    }

}
