package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.response.UserWithoutPassword;

import java.util.List;

public interface UserService {

    List<UserWithoutPassword> getAllUsers();

}
