package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.response.UserWithoutPassword;
import com.service.hotel_booking.mappers.UserMapper;
import com.service.hotel_booking.repositories.UserRepository;
import com.service.hotel_booking.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public List<UserWithoutPassword> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserWithoutPassword).toList();
    }

}
