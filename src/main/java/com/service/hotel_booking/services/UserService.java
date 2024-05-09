package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.UpdateUserStatusRequest;
import com.service.hotel_booking.entities.response.UserDetailResponse;
import com.service.hotel_booking.entities.response.UserProfileResponse;
import com.service.hotel_booking.services.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDetailResponse> getAllUsers(UserCriteria criteria, Pageable pageable);
    UserProfileResponse getUserProfile(Long id);
    UserDetailResponse getUserDetail(Long id);
    void updateUserStatus(Long id, UpdateUserStatusRequest status);

}
