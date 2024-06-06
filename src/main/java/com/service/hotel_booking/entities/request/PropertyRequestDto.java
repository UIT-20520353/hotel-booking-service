package com.service.hotel_booking.entities.request;

import com.service.hotel_booking.enumerations.PropertyType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class PropertyRequestDto {

    @NotBlank(message = PROPERTY_NAME_REQUIRED_ERROR)
    @Length(max = 255, message = PROPERTY_NAME_MAX_LENGTH_ERROR)
    private String name;

    @NotBlank(message = PROPERTY_DESCRIPTION_REQUIRED_ERROR)
    private String description;

    @NotBlank(message = PROPERTY_ADDRESS_REQUIRED_ERROR)
    @Length(max = 255, message = PROPERTY_ADDRESS_MAX_LENGTH_ERROR)
    private String address;

    @NotNull(message = PROPERTY_LONGITUDE_REQUIRED_ERROR)
    @DecimalMax(value = "180.0000000", message = PROPERTY_LONGITUDE_MAX_VALUE_ERROR)
    @DecimalMin(value = "-180.0000000", message = PROPERTY_LONGITUDE_MIN_VALUE_ERROR)
    @Digits(integer = 3, fraction = 7, message = PROPERTY_LONGITUDE_DIGITS_ERROR)
    private Double longitude;

    @NotNull(message = PROPERTY_LATITUDE_REQUIRED_ERROR)
    @DecimalMax(value = "90.0000000", message = PROPERTY_LATITUDE_MAX_VALUE_ERROR)
    @DecimalMin(value = "-90.0000000", message = PROPERTY_LATITUDE_MIN_VALUE_ERROR)
    @Digits(integer = 2, fraction = 7, message = PROPERTY_LATITUDE_DIGITS_ERROR)
    private Double latitude;

    @NotNull(message = PROPERTY_IMAGE_LIST_REQUIRED_ERROR)
    List<MultipartFile> imgList;

    @NotNull(message = PROPERTY_WARD_ID_REQUIRED_ERROR)
    private Integer wardId;

    @NotNull(message = PROPERTY_DISTRICT_ID_REQUIRED_ERROR)
    private Integer districtId;

    @NotNull(message = PROPERTY_PROVINCE_ID_REQUIRED_ERROR)
    private Integer provinceId;

    @NotNull(message = PROPERTY_ARGENT_ID_REQUIRED_ERROR)
    private Long argentId;

    private List<Long> amenityIds;

    @NotNull(message = PROPERTY_TYPE_REQUIRED_ERROR)
    private PropertyType type;

    private Long price;

    @NotNull(message = PROPERTY_DEPOSIT_REQUIRED_ERROR)
    private boolean deposit;

}
