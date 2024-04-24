package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.response.UserWithoutPassword;
import com.service.hotel_booking.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Resources")
@RequestMapping(value = "/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;


    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<UserWithoutPassword>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
