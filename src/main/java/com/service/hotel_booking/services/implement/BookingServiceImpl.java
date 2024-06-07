package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.exceptions.InternalException;
import com.service.hotel_booking.repositories.BookingRepository;
import com.service.hotel_booking.services.BookingService;
import com.service.hotel_booking.services.RoomService;
import com.service.hotel_booking.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {

    UserService userService;
    BookingRepository bookingRepository;
    RoomService roomService;

    @Override
    @Transactional
    public Long createBooking(Long userId, CreateBookingDto body) {
        User user = userService.getUserById(userId);
        List<Room> rooms = roomService.getRoomEntitiesByIdsAndPropertyId(body.getPropertyId(), body.getRoomIds());

        Long totalPrice = rooms.stream().map(Room::getPrice)
                                        .reduce(Long::sum)
                                        .orElseThrow(InternalException::new);
        return totalPrice;
    }

}
