package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.response.DetailProvinceResponse;
import com.service.hotel_booking.entities.response.DistrictResponse;
import com.service.hotel_booking.entities.response.SimpleProvinceResponse;
import com.service.hotel_booking.entities.response.WardResponse;
import com.service.hotel_booking.services.LocationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@Tag(name = "Location Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class LocationController {

    LocationService locationService;

//    @GetMapping(value = "/provinces")
//    private ResponseEntity<List<DetailProvinceResponse>> getAllProvinces() {
//        return ResponseEntity.ok(locationService.getAllProvinces());
//    }

    @GetMapping(value = "/provinces")
    private ResponseEntity<List<SimpleProvinceResponse>> getSimpleProvinces() {
        return ResponseEntity.ok(locationService.getSimpleProvinces());
    }

    @GetMapping(value = "/provinces/{provinceId}")
    private ResponseEntity<DetailProvinceResponse> getDetailProvince(@PathVariable Integer provinceId) {
        return ResponseEntity.ok(locationService.getProvinceById(provinceId));
    }

    @GetMapping(value = "/districts")
    private ResponseEntity<List<DistrictResponse>> getDistrictsByProvinceId(@Parameter Integer provinceId) {
        return ResponseEntity.ok(locationService.getDistrictsByProvinceId(provinceId));
    }

    @GetMapping(value = "/districts/{districtId}")
    public ResponseEntity<DistrictResponse> getDistrictById(@PathVariable Integer districtId) {
        return ResponseEntity.ok(locationService.getDistrictById(districtId));
    }

    @GetMapping(value = "/wards")
    public ResponseEntity<List<WardResponse>> getWardByDistrictId(@Parameter Integer districtId) {
        return ResponseEntity.ok(locationService.getWardsByDistrictId(districtId));
    }

    @GetMapping(value = "/wards/{wardId}")
    public ResponseEntity<WardResponse> getWardById(@PathVariable Integer wardId) {
        return ResponseEntity.ok(locationService.getWardById(wardId));
    }

}
