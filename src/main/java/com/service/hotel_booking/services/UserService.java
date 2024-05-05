package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.response.UserWithoutPassword;
import com.service.hotel_booking.services.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserWithoutPassword> getAllUsers(UserCriteria criteria, Pageable pageable);

}
