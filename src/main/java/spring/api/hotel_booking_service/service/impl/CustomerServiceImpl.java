package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;
import spring.api.hotel_booking_service.entity.Customer;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.helper.enumeration.CustomerStatus;
import spring.api.hotel_booking_service.helper.enumeration.UserRole;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.repository.CustomerRepository;
import spring.api.hotel_booking_service.repository.UserRepository;
import spring.api.hotel_booking_service.service.CustomerService;
import spring.api.hotel_booking_service.service.UserService;

import static spring.api.hotel_booking_service.helper.constant.Message.USER_EMAIL_EXISTED;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    BCryptPasswordEncoder passwordEncoder;
    UserService userService;

    @Override
    @Transactional
    public void createCustomer(UserRegisterDto requestDto) {
        if (userService.isEmailExist(requestDto.getEmail())) {
            throw new BadRequestException(USER_EMAIL_EXISTED);
        }

        User user = userService.save(User.builder()
                                            .email(requestDto.getEmail())
                                            .password(passwordEncoder.encode(requestDto.getPassword()))
                                            .role(UserRole.CUSTOMER)
                                            .build());
        customerRepository.save(Customer.builder()
                                        .firstName(requestDto.getFirstName())
                                        .lastName(requestDto.getLastName())
                                        .dateOfBirth(requestDto.getDateOfBirth())
                                        .status(CustomerStatus.ACTIVE)
                                        .gender(requestDto.getGender())
                                        .user(user)
                                        .build());
    }
}
