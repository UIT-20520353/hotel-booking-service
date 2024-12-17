package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.helper.exception.AuthenticationException;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.repository.UserRepository;
import spring.api.hotel_booking_service.service.UserService;

import static spring.api.hotel_booking_service.helper.constant.Message.INVALID_CREDENTIAL_ERR;
import static spring.api.hotel_booking_service.helper.constant.Message.USER_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User findUserByEmail(String email, Boolean isLogin) {
        if (isLogin) {
            return userRepository.findByEmail(email)
                                 .orElseThrow(() -> new AuthenticationException(INVALID_CREDENTIAL_ERR));
        }
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND_ERROR));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND_ERROR));
    }
}
