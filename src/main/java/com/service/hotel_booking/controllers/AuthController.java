package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.ArgentRegisterRequest;
import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value ="/api")
@Tag(name = "Auth Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

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
    public ResponseEntity<Void> argentRegister(@Valid @ModelAttribute ArgentRegisterRequest anotherData,
                                               @RequestPart(name = "frontIdentityCard", required = true) MultipartFile frontIdentityCard,
                                               @RequestPart(name = "backIdentityCard", required = true) MultipartFile backIdentityCard,
                                               @RequestPart(name = "selfieImg", required = true) MultipartFile selfieImg) {
        authService.argentRegister(anotherData, frontIdentityCard, backIdentityCard, selfieImg);
        return ResponseEntity.noContent().build();
    }

}
