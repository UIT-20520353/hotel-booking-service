package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.hotel_booking_service.dto.admin.CreateAdminDto;
import spring.api.hotel_booking_service.service.SystemAdminService;

@Tag(name = "Private")
@RequestMapping("/api/private")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PrivateController {

    SystemAdminService systemAdminService;

    @PostMapping("/admin")
    public ResponseEntity<Void> createAdmin(@RequestBody CreateAdminDto createAdminDto) {
        systemAdminService.createAdmin(createAdminDto);
        return ResponseEntity.ok().build();
    }

}
