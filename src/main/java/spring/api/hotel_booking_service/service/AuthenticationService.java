package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.login.RequestDto;
import spring.api.hotel_booking_service.dto.login.ResponseDto;

public interface AuthenticationService {

    ResponseDto login(RequestDto requestDto);

}
