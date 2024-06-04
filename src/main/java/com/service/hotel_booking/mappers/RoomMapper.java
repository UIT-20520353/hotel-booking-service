package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.response.RoomDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomMapper {

    public RoomDto toRoomDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getPrice(),
                room.getStatus()
        );
    }

}
