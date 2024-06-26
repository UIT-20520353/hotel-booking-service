package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.UpdateUserStatusRequest;
import com.service.hotel_booking.entities.response.UserDetailDto;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.criteria.UserCriteria;
import com.service.hotel_booking.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Resources")
@RequestMapping(value = "/api/admin/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDetailDto>> getAllUsers(UserCriteria criteria,
                                                           @ParameterObject @PageableDefault Pageable pageable) {
        final Page<UserDetailDto> page = userService.getAllUsers(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDto> getUserDetail(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDetail(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Void> updateUserStatus(@PathVariable Long userId,
                                                 @RequestBody UpdateUserStatusRequest body) {
        userService.updateUserStatus(userId, body);
        return ResponseEntity.noContent().build();
    }

}
