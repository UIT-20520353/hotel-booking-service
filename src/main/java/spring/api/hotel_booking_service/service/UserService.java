package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.entity.User;

public interface UserService {

    User findUserByEmail(String email, Boolean isLogin);
    User findUserByEmail(String email);
    Boolean isEmailExist(String email);
    User save(User user);
    User findUserById(Long id);

}
