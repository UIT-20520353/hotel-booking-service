package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Property;
import com.service.hotel_booking.entities.PropertyAmenity;
import com.service.hotel_booking.entities.PropertyImage;
import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.response.AmenityWithoutTypeDto;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.entities.response.PropertyImageDto;
import com.service.hotel_booking.entities.response.RoomDto;
import com.service.hotel_booking.entities.response.booking.BookingPropertyDto;
import com.service.hotel_booking.enumerations.RoomStatus;
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
public class PropertyMapper {

    UserMapper userMapper;
    ProvinceMapper provinceMapper;
    AmenityMapper amenityMapper;
    RoomMapper roomMapper;

    public PropertyDetailDto toPropertyDetail(Property property) {
        List<PropertyImageDto> images = property.getImages() == null ?
                new ArrayList<>() :
                property.getImages().stream().map(this::toPropertyImageDto).toList();
        List<RoomDto> rooms = property.getRooms() == null ?
                new ArrayList<>() :
                property.getRooms().stream()
                        .filter(room -> !room.getStatus().equals(RoomStatus.DELETED))
                        .map(roomMapper::toRoomDto).toList();
        List<AmenityWithoutTypeDto> amenities = property.getAmenities() == null ?
                new ArrayList<>() :
                property.getAmenities().stream()
                        .map(PropertyAmenity::getAmenity)
                        .map(amenityMapper::toAmenityWithoutTypeDto)
                        .collect(Collectors.toList());

        return new PropertyDetailDto(
                property.getId(),
                property.getName(),
                property.getDescription(),
                property.getAddress(),
                property.getLongitude(),
                property.getLatitude(),
                property.getDepositPercent(),
                property.getStatus(),
                images,
                amenities,
                provinceMapper.toPropertyProvince(property.getProvince(), property.getDistrict(), property.getWard()),
                userMapper.toUserProfile(property.getArgent()),
                rooms
        );
    }

    public BookingPropertyDto toBookingPropertyDto(Property property, List<Room> rooms) {
        List<PropertyImageDto> images = property.getImages() == null ?
                new ArrayList<>() :
                property.getImages().stream().map(this::toPropertyImageDto).toList();

        return new BookingPropertyDto(
                property.getId(),
                property.getName(),
                property.getAddress(),
                property.getDescription(),
                provinceMapper.toPropertyProvince(property.getProvince(), property.getDistrict(), property.getWard()),
                property.getLongitude(),
                property.getLatitude(),
                images,
                property.getType(),
                rooms.stream().map(roomMapper::toRoomDto).toList()
        );
    }

    private PropertyImageDto toPropertyImageDto(PropertyImage propertyImage) {
        return new PropertyImageDto(
                propertyImage.getId(),
                propertyImage.getUrl()
        );
    }

}
