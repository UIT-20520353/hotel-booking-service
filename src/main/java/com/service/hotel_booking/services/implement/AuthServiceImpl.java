package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.config.jwt.GenerateJwtResult;
import com.service.hotel_booking.config.jwt.JwtProvider;
import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.UserSession;
import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.EUserRole;
import com.service.hotel_booking.exceptions.AuthenticationException;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.repositories.UserRepository;
import com.service.hotel_booking.services.AuthService;
import com.service.hotel_booking.services.UserSessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    UserSessionService userSessionService;
    UserRepository userRepository;
    JwtProvider jwtProvider;
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthLoginResponse login(AuthLoginRequest body, EUserRole role) {
        if (!userRepository.existsByEmail(body.getEmail())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        User user = userRepository.findByEmail(body.getEmail());

        if (EUserRole.ADMIN.equals(role) && !EUserRole.ADMIN.equals(user.getRole())) {
            throw new AuthenticationException(USER_IS_NOT_ADMIN);
        }

        if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        GenerateJwtResult jwtTokens = jwtProvider.generateToken(user);
        user.setNewSession(new UserSession(jwtTokens.tokenId(), jwtTokens.expiredDate()));
        return new AuthLoginResponse(jwtTokens.accessToken());
    }

    @Override
    public void register(AuthRegisterRequest body) {
        if (userRepository.existsByEmail(body.getEmail())) {
            throw new BadRequestException(USER_ALREADY_EXIST_ERR);
        }

        userRepository.save(User.builder()
                                .firstName(body.getFirstName())
                                .lastName(body.getLastName())
                                .email(body.getEmail())
                                .phoneNumber(body.getPhoneNumber())
                                .password(passwordEncoder.encode(body.getPassword()))
                                .role(EUserRole.USER)
                                .build());
    }

}
