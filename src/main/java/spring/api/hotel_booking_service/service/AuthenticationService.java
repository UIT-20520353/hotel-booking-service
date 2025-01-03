package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;
import spring.api.hotel_booking_service.dto.profile.CustomerDto;
import spring.api.hotel_booking_service.dto.register.BusinessOwnerRegister;
import spring.api.hotel_booking_service.dto.register.UserRegisterDto;

public interface AuthenticationService {

    ResponseDto login(RequestDto requestDto);
    void register(UserRegisterDto requestDto);
    CustomerDto getCustomerProfile();
    void registerBusinessOwner(BusinessOwnerRegister requestDto);

}
