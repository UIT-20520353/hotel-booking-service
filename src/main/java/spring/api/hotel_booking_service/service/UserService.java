package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.entity.User;

public interface UserService {

    User findUserByEmail(String email, Boolean isLogin);
    User findUserByEmail(String email);

}
