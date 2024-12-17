package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.config.jwt.GenerateJwtResult;
import spring.api.hotel_booking_service.config.jwt.JwtProvider;
import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;
import spring.api.hotel_booking_service.entity.SystemAdmin;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.entity.UserSession;
import spring.api.hotel_booking_service.helper.exception.AuthenticationException;
import spring.api.hotel_booking_service.service.AuthenticationService;
import spring.api.hotel_booking_service.service.UserService;

import static spring.api.hotel_booking_service.helper.constant.Message.INVALID_CREDENTIAL_ERR;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    UserService userService;
    BCryptPasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    @Override
    public ResponseDto login(RequestDto requestDto) {
        User user = userService.findUserByEmail(requestDto.getEmail(), true);

        System.out.println(passwordEncoder.encode("Admin@123456"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        GenerateJwtResult jwtTokens = jwtProvider.generateToken(user);
        user.setNewSession(new UserSession(jwtTokens.tokenId(), jwtTokens.expiredDate()));

        return new ResponseDto(jwtTokens.accessToken(), jwtTokens.refreshToken());
    }

    @Override
    public void register(UserRegisterDto requestDto) {

    }

}
