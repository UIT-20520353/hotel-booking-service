package com.service.hotel_booking.entities.request;

import com.service.hotel_booking.enumerations.AmenityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class CreateAmenityDto {

    @NotBlank(message = AMENITY_REQUIRED_ERROR)
    @Length(max = 100, message = AMENITY_NAME_MAX_LENGTH_ERROR)
    private String name;

    @NotNull(message = AMENITY_TYPE_REQUIRED_ERROR)
    private AmenityType type;

}
