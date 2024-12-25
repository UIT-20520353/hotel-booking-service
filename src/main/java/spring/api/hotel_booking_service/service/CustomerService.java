package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.register.UserRegisterDto;

public interface CustomerService {

    void createCustomer(UserRegisterDto requestDto);

}
