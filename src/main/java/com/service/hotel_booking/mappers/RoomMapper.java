package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.PropertyAmenity;
import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.RoomAmenity;
import com.service.hotel_booking.entities.RoomImage;
import com.service.hotel_booking.entities.response.AmenityDto;
import com.service.hotel_booking.entities.response.AmenityWithoutTypeDto;
import com.service.hotel_booking.entities.response.RoomDto;
import com.service.hotel_booking.entities.response.RoomImageDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomMapper {

    AmenityMapper amenityMapper;

    public RoomDto toRoomDto(Room room) {
        List<RoomImageDto> images = room.getImages() == null ?
                new ArrayList<>() :
                room.getImages().stream().map(this::toRoomImageDto).toList();
        List<AmenityWithoutTypeDto> amenities = room.getAmenities() == null ?
                new ArrayList<>() :
                room.getAmenities().stream()
                        .map(RoomAmenity::getAmenity)
                        .map(amenityMapper::toAmenityWithoutTypeDto)
                        .collect(Collectors.toList());

        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getPrice(),
                room.getStatus(),
                images,
                amenities
        );
    }

    private RoomImageDto toRoomImageDto(RoomImage roomImage) {
        return new RoomImageDto(
                roomImage.getId(),
                roomImage.getUrl()
        );
    }

}
