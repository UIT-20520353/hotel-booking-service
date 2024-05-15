package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.HotelService;
import com.service.hotel_booking.entities.request.CreateHotelServiceDtoRequest;
import com.service.hotel_booking.entities.response.HotelServiceResponseDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.HotelServiceMapper;
import com.service.hotel_booking.repositories.HotelServiceRepository;
import com.service.hotel_booking.services.HotelServiceService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.service.hotel_booking.constant.MessageConstant.HOTEL_SERVICE_NOT_EXIST;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelServiceServiceImpl implements HotelServiceService {

    HotelServiceRepository hotelServiceRepository;
    HotelServiceMapper hotelServiceMapper;

    @Override
    public Page<HotelServiceResponseDto> getAllHotelServices(Pageable pageable) {
        return hotelServiceRepository.findAll(pageable).map(hotelServiceMapper::toHotelServiceDto);
    }

    @Override
    @Transactional
    public void createHotelService(CreateHotelServiceDtoRequest body) {
        hotelServiceRepository.save(HotelService
                                        .builder()
                                        .name(body.getName())
                                        .build());
    }

    @Override
    @Transactional
    public void deleteHotelService(Long id) {
        HotelService hotelService = hotelServiceRepository
                                        .findById(id)
                                        .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        hotelService.setIsDeleted(true);
    }

    @Override
    public void updateHotelService(Long id, CreateHotelServiceDtoRequest body) {
        HotelService hotelService = hotelServiceRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(HOTEL_SERVICE_NOT_EXIST));

        hotelService.setName(body.getName());
    }

}
