package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.entity.HotelAmenity;
import spring.api.hotel_booking_service.repository.HotelAmenityRepository;
import spring.api.hotel_booking_service.service.HotelAmenityService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HotelAmenityServiceImpl implements HotelAmenityService {

    HotelAmenityRepository hotelAmenityRepository;

    @Override
    @Transactional
    public HotelAmenity save(HotelAmenity hotelAmenity) {
        return hotelAmenityRepository.save(hotelAmenity);
    }
}
