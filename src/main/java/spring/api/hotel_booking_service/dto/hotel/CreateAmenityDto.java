package spring.api.hotel_booking_service.dto.hotel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Data
public class CreateAmenityDto {

    @NotBlank(message = HOTEL_AMENITY_NAME_REQUIRED_ERROR)
    private String name;

    @NotBlank(message = HOTEL_AMENITY_UNIT_REQUIRED_ERROR)
    private String unit;

    @NotNull(message = HOTEL_AMENITY_PRICE_REQUIRED_ERROR)
    @Min(value = 0, message = HOTEL_AMENITY_PRICE_MIN_ERROR)
    private Integer price;

}
