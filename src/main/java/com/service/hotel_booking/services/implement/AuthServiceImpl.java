package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.config.jwt.GenerateJwtResult;
import com.service.hotel_booking.config.jwt.JwtProvider;
import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.Argent;
import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.UserSession;
import com.service.hotel_booking.entities.request.ArgentRegisterRequest;
import com.service.hotel_booking.entities.request.AuthLoginRequest;
import com.service.hotel_booking.entities.request.AuthRegisterRequest;
import com.service.hotel_booking.entities.response.AuthLoginResponse;
import com.service.hotel_booking.enumerations.UserRole;
import com.service.hotel_booking.enumerations.UserStatus;
import com.service.hotel_booking.exceptions.AuthenticationException;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.repositories.ArgentRepository;
import com.service.hotel_booking.repositories.UserRepository;
import com.service.hotel_booking.services.AuthService;
import com.service.hotel_booking.services.ResourceService;
import com.service.hotel_booking.services.UserSessionService;
import com.service.hotel_booking.utils.FileUtils;
import jakarta.transaction.Transactional;
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
    ArgentRepository argentRepository;
    JwtProvider jwtProvider;
    BCryptPasswordEncoder passwordEncoder;
    ResourceService resourceService;

    @Override
    @Transactional
    public AuthLoginResponse login(AuthLoginRequest body, UserRole role) {
        if (!userRepository.existsByEmail(body.getEmail())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        User user = userRepository.findByEmail(body.getEmail());

        if (UserRole.AGENT.equals(user.getRole()) && UserStatus.CREATED.equals(user.getStatus())) {
            throw new BadRequestException(ACCOUNT_PENDING_APPROVAL);
        }

        if (UserRole.ADMIN.equals(role) && !UserRole.ADMIN.equals(user.getRole())) {
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
    @Transactional
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
                                .role(UserRole.USER)
                                .status(UserStatus.ACTIVE)
                                .build());
    }

    @Override
    @Transactional
    public void logout() {
        String tokenId = SecurityUtils.getCurrentTokenId();
        userSessionService.removeExpiredSession(tokenId);
    }

    @Override
    @Transactional
    public void argentRegister(ArgentRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(USER_ALREADY_EXIST_ERR);
        }

        FileUtils.checkMultipartFileNull(request.getFrontIdentityCard(), FRONT_IDENTITY_CARD_REQUIRED_ERROR);
        FileUtils.checkMultipartFileNull(request.getBackIdentityCard(), BACK_IDENTITY_CARD_REQUIRED_ERROR);
        FileUtils.checkMultipartFileNull(request.getSelfieImg(), SELFIE_IMG_REQUIRED_ERROR);

        User user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .phoneNumber(request.getPhoneNumber())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(UserRole.AGENT)
                        .status(UserStatus.CREATED)
                        .build();
        userRepository.save(user);

        String frontUrl = resourceService.uploadImage(request.getFrontIdentityCard());
        String backUrl = resourceService.uploadImage(request.getBackIdentityCard());
        String selfieUrl = resourceService.uploadImage(request.getSelfieImg());
        argentRepository.save(Argent
                                      .builder()
                                      .identityNumber(request.getIdentityNumber())
                                      .frontIdentityCard(frontUrl)
                                      .backIdentityCard(backUrl)
                                      .selfieImg(selfieUrl)
                                      .user(user)
                                      .build());
    }

}
