package com.service.hotel_booking.controllers;

import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.request.ArgentRegisterRequest;
import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.entities.response.UserProfileResponse;
import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.services.AuthService;
import com.service.hotel_booking.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/api")
@Tag(name = "Auth Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;
    UserService userService;

    @PostMapping("/admin/login")
    public ResponseEntity<AuthLoginResponse> adminLogin(@Valid @RequestBody  AuthLoginRequest body) {
        return ResponseEntity.ok(authService.login(body, UserRole.ADMIN));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody  AuthLoginRequest body) {
        return ResponseEntity.ok(authService.login(body, UserRole.USER));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody AuthRegisterRequest body) {
        authService.register(body);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/argent/register",
                 consumes = { "multipart/form-data" })
    public ResponseEntity<Void> argentRegister(@Valid @ModelAttribute ArgentRegisterRequest anotherData) {
        authService.argentRegister(anotherData);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile(SecurityUtils.getCurrentUserId()));
    }

}
