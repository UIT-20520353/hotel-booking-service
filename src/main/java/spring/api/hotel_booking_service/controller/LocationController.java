package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.hotel_booking_service.dto.AdministrativeRegionDto;
import spring.api.hotel_booking_service.dto.AdministrativeUnitDto;
import spring.api.hotel_booking_service.dto.ProvinceDto;
import spring.api.hotel_booking_service.dto.WardDto;
import spring.api.hotel_booking_service.dto.DistrictDto;
import spring.api.hotel_booking_service.service.*;

import java.util.List;

@Tag(name = "Location")
@RequestMapping("/api/common")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LocationController {

    AdministrativeRegionService administrativeRegionService;
    AdministrativeUnitService administrativeUnitService;
    ProvinceService provinceService;
    DistrictService districtService;
    WardService wardService;

    private final JavaMailSender mailSender;

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

    @GetMapping("/administrative-regions")
    public ResponseEntity<List<AdministrativeRegionDto>> getAllRegions() {
        return ResponseEntity.ok(administrativeRegionService.getAllRegions());
    }

    @GetMapping("/administrative-units")
    public ResponseEntity<List<AdministrativeUnitDto>> getAllAdministrativeUnits() {
        return ResponseEntity.ok(administrativeUnitService.getAllAdministrativeUnits());
    }

    @GetMapping("/test")
    public void test() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ngohoang8320@gmail.com");
        message.setTo("ngocanh2210200@gmail.com");
        message.setSubject("Test");
        message.setText("This is a test");
        mailSender.send(message);
    }

}
