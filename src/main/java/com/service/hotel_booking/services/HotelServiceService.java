package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.CreateHotelServiceDtoRequest;
import com.service.hotel_booking.entities.response.HotelServiceResponseDto;
import com.service.hotel_booking.services.criteria.HotelServiceCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelServiceService {

    Page<HotelServiceResponseDto> getAllHotelServices(HotelServiceCriteria criteria, Pageable pageable);
    void createHotelService(CreateHotelServiceDtoRequest body);
    void deleteHotelService(Long id);
    void updateHotelService(Long id, CreateHotelServiceDtoRequest body);

}
