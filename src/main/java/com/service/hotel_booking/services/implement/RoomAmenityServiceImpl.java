package com.service.hotel_booking.services.implement;


import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.RoomAmenity;
import com.service.hotel_booking.repositories.RoomAmenityRepository;
import com.service.hotel_booking.services.RoomAmenityService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomAmenityServiceImpl implements RoomAmenityService {

    RoomAmenityRepository roomAmenityRepository;

    @Override
    @Transactional
    public void createRoomAmenity(Room room, Amenity amenity) {
        roomAmenityRepository.save(RoomAmenity.builder()
                                              .room(room)
                                              .amenity(amenity)
                                              .build());
    }

}
