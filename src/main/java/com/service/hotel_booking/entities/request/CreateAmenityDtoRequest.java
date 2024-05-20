package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static com.service.hotel_booking.constant.MessageConstant.HOTEL_SERVICE_NAME_MAX_LENGTH_ERROR;
import static com.service.hotel_booking.constant.MessageConstant.HOTEL_SERVICE_REQUIRED_ERROR;

@Data
public class CreateAmenityDtoRequest {

    @NotBlank(message = HOTEL_SERVICE_REQUIRED_ERROR)
    @Length(max = 100, message = HOTEL_SERVICE_NAME_MAX_LENGTH_ERROR)
    private String name;

}
