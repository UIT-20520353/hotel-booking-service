package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.request.CreateRoomDto;
import com.service.hotel_booking.entities.response.RoomDto;

import java.util.List;

public interface RoomService {

    void createRoom(CreateRoomDto body);
    List<RoomDto> getAllRooms(Long propertyId);
    void deleteRoom(Long roomId);
    Room getRoomEntityById(Long roomId);
    List<Room> getRoomEntitiesByIds(List<Long> roomIds);
    List<Room> getRoomEntitiesByIdsAndPropertyId(Long propertyId, List<Long> roomIds);

}
