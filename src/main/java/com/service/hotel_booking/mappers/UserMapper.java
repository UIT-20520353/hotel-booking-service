package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.response.UserWithoutPassword;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserMapper {

    ArgentMapper argentMapper;

    public UserWithoutPassword toUserWithoutPassword(User user) {
        return new UserWithoutPassword(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                argentMapper.toArgentResponse(user.getArgent()));
    }

}
