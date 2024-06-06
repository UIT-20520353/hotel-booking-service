package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.RoomImage;
import com.service.hotel_booking.repositories.RoomImageRepository;
import com.service.hotel_booking.services.RoomImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomImageServiceImpl implements RoomImageService {

    RoomImageRepository roomImageRepository;

    @Override
    public void createRoomImage(Room room, String url) {
        roomImageRepository.save(RoomImage.builder()
                                          .room(room)
                                          .url(url)
                                          .build());
    }

}
