package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.EUserRole;
import com.service.hotel_booking.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
@Tag(name = "Auth Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody  AuthLoginRequest body) {
        return ResponseEntity.ok(authService.login(body, EUserRole.USER));
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

}
