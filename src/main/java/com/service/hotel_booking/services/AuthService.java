package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.ArgentRegisterRequest;
import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.UserRole;

public interface AuthService {

    AuthLoginResponse login(AuthLoginRequest body, UserRole role);
    void register(AuthRegisterRequest body);
    void logout();
    void argentRegister(ArgentRegisterRequest request);

}
