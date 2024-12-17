package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.hotel_booking_service.dto.admin.AdminProfileDto;
import spring.api.hotel_booking_service.service.SystemAdminService;

@Tag(name = "Admin resources")
@RequestMapping("/api/admin")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminController {

    SystemAdminService systemAdminService;

    @GetMapping("/profile")
    public ResponseEntity<AdminProfileDto> getProfile() {
        return ResponseEntity.ok().body(systemAdminService.getProfile());
    }

}
