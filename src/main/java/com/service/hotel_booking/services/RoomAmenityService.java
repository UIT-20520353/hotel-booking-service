package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Room;

public interface RoomAmenityService {

    void createRoomAmenity(Room room, Amenity amenity);
}
