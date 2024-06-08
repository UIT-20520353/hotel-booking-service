package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Booking;
import com.service.hotel_booking.entities.BookingRoom;
import com.service.hotel_booking.entities.Property;
import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.response.booking.BookingDetailDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingMapper {

    BankMapper bankMapper;
    PropertyMapper propertyMapper;

    public BookingDetailDto toBookingDetailDto(Booking booking,
                                               long depositMoney,
                                               Property property,
                                               List<Room> rooms) {
        return new BookingDetailDto(
                booking.getId(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalPrice(),
                depositMoney,
                booking.getStatus(),
                booking.getCreateAt(),
                property.getArgent().getArgent().getBanks().stream().map(bankMapper::toBankDetailDto).toList(),
                propertyMapper.toBookingPropertyDto(property, rooms)
        );
    }

    public BookingDetailDto toBookingDetailDto(Booking booking) {
        long depositMoney = booking.getTotalPrice() * booking.getProperty().getDepositPercent() / 100;
        Property property = booking.getProperty();
        List<Room> rooms = booking.getRooms().stream().map(BookingRoom::getRoom).toList();

        return new BookingDetailDto(
                booking.getId(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getTotalPrice(),
                depositMoney,
                booking.getStatus(),
                booking.getCreateAt(),
                property.getArgent().getArgent().getBanks().stream().map(bankMapper::toBankDetailDto).toList(),
                propertyMapper.toBookingPropertyDto(property, rooms)
        );
    }

}
