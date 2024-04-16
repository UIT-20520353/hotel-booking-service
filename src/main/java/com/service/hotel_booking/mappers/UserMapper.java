package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.response.UserWithoutPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserWithoutPassword toUserWithoutPassword(User user) {
        return new UserWithoutPassword(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail());
    }

}
