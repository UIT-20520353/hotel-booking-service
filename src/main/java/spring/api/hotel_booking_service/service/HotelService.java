package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.hotel.CreateAmenityDto;
import spring.api.hotel_booking_service.dto.hotel.CreateHotelDto;
import spring.api.hotel_booking_service.entity.Hotel;

public interface HotelService {

    void createHotel(CreateHotelDto body);
    void deleteHotel(Long id);
    void addAmenity(Long id, CreateAmenityDto body);
    Hotel getHotel(Long id);

}
