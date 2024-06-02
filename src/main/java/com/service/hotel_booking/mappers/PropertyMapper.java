package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Property;
import com.service.hotel_booking.entities.PropertyImage;
import com.service.hotel_booking.entities.response.PropertyDetailDto;
import com.service.hotel_booking.entities.response.PropertyImageDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyMapper {

    UserMapper userMapper;
    ProvinceMapper provinceMapper;
    AmenityMapper amenityMapper;

    public PropertyDetailDto toPropertyDetail(Property property, List<Amenity> amenities) {
        List<PropertyImageDto> images = property.getImages() == null ?
                new ArrayList<>() :
                property.getImages().stream().map(this::toPropertyImageDto).toList();

        return new PropertyDetailDto(
                property.getId(),
                property.getName(),
                property.getDescription(),
                property.getAddress(),
                property.getLongitude(),
                property.getLatitude(),
                property.getStatus(),
                property.getPrice(),
                images,
                amenities.stream().map(amenityMapper::toAmenityDto).toList(),
                provinceMapper.toPropertyProvince(property.getProvince(), property.getDistrict(), property.getWard()),
                userMapper.toUserProfile(property.getArgent())
        );
    }

    private PropertyImageDto toPropertyImageDto(PropertyImage propertyImage) {
        return new PropertyImageDto(
                propertyImage.getId(),
                propertyImage.getUrl()
        );
    }

}
