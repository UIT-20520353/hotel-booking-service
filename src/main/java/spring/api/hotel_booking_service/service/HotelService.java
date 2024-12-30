package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.hotel.CreateHotelDto;

public interface HotelService {

    void createHotel(CreateHotelDto body);
    void deleteHotel(Long id);

}
