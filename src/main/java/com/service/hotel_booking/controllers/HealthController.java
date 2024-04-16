package com.service.hotel_booking.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Check")
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<String> checkServer() {
        return ResponseEntity.ok("OK");
    }

}
