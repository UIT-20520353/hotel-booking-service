package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.config.jwt.GenerateJwtResult;
import spring.api.hotel_booking_service.config.jwt.JwtProvider;
import spring.api.hotel_booking_service.config.jwt.SecurityUtils;
import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;
import spring.api.hotel_booking_service.dto.profile.CustomerDto;
import spring.api.hotel_booking_service.dto.register.BusinessOwnerRegister;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;
import spring.api.hotel_booking_service.entity.BusinessOwner;
import spring.api.hotel_booking_service.entity.Customer;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.entity.UserSession;
import spring.api.hotel_booking_service.helper.enumeration.BusinessOwnerStatus;
import spring.api.hotel_booking_service.helper.enumeration.UserRole;
import spring.api.hotel_booking_service.helper.exception.AuthenticationException;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.helper.mapper.CustomerMapper;
import spring.api.hotel_booking_service.repository.CustomerRepository;
import spring.api.hotel_booking_service.repository.UserRepository;
import spring.api.hotel_booking_service.service.AuthenticationService;
import spring.api.hotel_booking_service.service.BusinessOwnerService;
import spring.api.hotel_booking_service.service.CustomerService;
import spring.api.hotel_booking_service.service.UserService;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    UserService userService;
    BCryptPasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;
    CustomerService customerService;
    CustomerMapper customerMapper;
    UserRepository userRepository;
    CustomerRepository customerRepository;
    BusinessOwnerService businessOwnerService;

    @Override
    public ResponseDto login(RequestDto requestDto) {
        User user = userService.findUserByEmail(requestDto.getEmail(), true);

        if (!user.getRole().equals(requestDto.getLoginType())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException(INVALID_CREDENTIAL_ERR);
        }

        if (user.getRole().equals(UserRole.BUSINESS_OWNER)) {
            BusinessOwner businessOwner = businessOwnerService.findBusinessOwnerByEmail(user.getEmail());

            switch (businessOwner.getStatus()) {
                case PENDING:
                    throw new AuthenticationException(BUSINESS_OWNER_ACCOUNT_PENDING);
                case REJECTED:
                    throw new AuthenticationException(BUSINESS_OWNER_ACCOUNT_REJECTED);
                default:
                    break;
            }
        }

        GenerateJwtResult jwtTokens = jwtProvider.generateToken(user);
        user.setNewSession(new UserSession(jwtTokens.tokenId(), jwtTokens.expiredDate()));

        return new ResponseDto(jwtTokens.accessToken(), jwtTokens.refreshToken());
    }

    @Override
    public void register(UserRegisterDto requestDto) {
        customerService.createCustomer(requestDto);
    }

    @Override
    public CustomerDto getCustomerProfile() {
        User user = userRepository.findById(SecurityUtils.getCurrentUserId())
                                  .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND_ERROR));
        Customer customer = customerRepository.findByUserId(user.getId())
                                              .orElseThrow(() -> new BadRequestException(USER_NOT_FOUND_ERROR));
        return customerMapper.toCustomerDto(user, customer);
    }

    @Override
    public void registerBusinessOwner(BusinessOwnerRegister requestDto) {
        businessOwnerService.createBusinessOwner(requestDto);
    }

}
