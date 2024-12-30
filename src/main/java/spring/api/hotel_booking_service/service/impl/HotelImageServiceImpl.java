package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.entity.HotelImage;
import spring.api.hotel_booking_service.repository.HotelImageRepository;
import spring.api.hotel_booking_service.service.HotelImageService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HotelImageServiceImpl implements HotelImageService {

    HotelImageRepository hotelImageRepository;

    @Override
    @Transactional
    public HotelImage createHotelImage(HotelImage hotelImage) {
        return hotelImageRepository.save(hotelImage);
    }

}
