package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.EUserRole;

public interface AuthService {

    AuthLoginResponse login(AuthLoginRequest body, EUserRole role);
    void register(AuthRegisterRequest body);

}
