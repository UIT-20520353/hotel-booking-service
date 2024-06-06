package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class CreateRoomDto {

    @NotBlank(message = ROOM_NAME_REQUIRED_ERROR)
    @Length(max = 255, message = ROOM_NAME_MAX_LENGTH_ERROR)
    private String name;

    @NotNull(message = ROOM_PRICE_REQUIRED_ERROR)
    @Digits(integer = 11, fraction = 0, message = ROOM_PRICE_INTEGER_ERROR)
    private Long price;

    @NotNull(message = ROOM_PROPERTY_ID_REQUIRED_ERROR)
    private Long propertyId;

    private List<Long> amenityIds;

    @NotNull(message = ROOM_IMAGE_LIST_REQUIRED_ERROR)
    List<MultipartFile> imgList;

}
