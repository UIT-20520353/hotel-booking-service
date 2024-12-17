package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.service.AuthenticationService;
import spring.api.hotel_booking_service.service.UserService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    UserService userService;

    @Override
    public ResponseDto login(RequestDto requestDto) {
        User user = userService.findUserByEmail(requestDto.getEmail(), true);
        return new ResponseDto(user.getEmail());
    }

    @Override
    public void register(UserRegisterDto requestDto) {

    }

}
