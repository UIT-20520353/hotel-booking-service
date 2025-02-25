package spring.api.hotel_booking_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;
import spring.api.hotel_booking_service.dto.profile.CustomerDto;
import spring.api.hotel_booking_service.dto.register.BusinessOwnerRegister;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;
import spring.api.hotel_booking_service.service.AuthenticationService;

@Tag(name = "Authentication")
@RequestMapping("/api/authentication")
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(authenticationService.login(requestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterDto requestDto) {
        authenticationService.register(requestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile/customer")
    public ResponseEntity<CustomerDto> getCustomerProfile() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/register/business-owner", consumes = "multipart/form-data")
    public ResponseEntity<Void> registerBusinessOwner(@Valid @ModelAttribute BusinessOwnerRegister requestDto) {
        authenticationService.registerBusinessOwner(requestDto);
        return ResponseEntity.noContent().build();
    }

}
