package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Property;
import com.service.hotel_booking.entities.PropertyAmenity;
import com.service.hotel_booking.entities.PropertyImage;
import com.service.hotel_booking.entities.response.*;
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
                property.getRooms().stream().map(roomMapper::toRoomDto).toList();
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
                property.getStatus(),
                images,
                amenities,
                provinceMapper.toPropertyProvince(property.getProvince(), property.getDistrict(), property.getWard()),
                userMapper.toUserProfile(property.getArgent()),
                rooms
        );
    }

    private PropertyImageDto toPropertyImageDto(PropertyImage propertyImage) {
        return new PropertyImageDto(
                propertyImage.getId(),
                propertyImage.getUrl()
        );
    }

}
