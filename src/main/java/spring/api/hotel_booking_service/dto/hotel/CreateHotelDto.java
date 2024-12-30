package spring.api.hotel_booking_service.dto.hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Data
public class CreateHotelDto {

    @NotBlank(message = HOTEL_NAME_REQUIRED_ERROR)
    private String name;

    @NotBlank(message = HOTEL_ADDRESS_REQUIRED_ERROR)
    private String address;

    @NotBlank(message = HOTEL_DESCRIPTION_REQUIRED_ERROR)
    private String description;

    @NotNull(message = HOTEL_LATITUDE_REQUIRED_ERROR)
    private BigDecimal latitude;

    @NotNull(message = HOTEL_LONGITUDE_REQUIRED_ERROR)
    private BigDecimal longitude;

    @NotNull(message = HOTEL_OVERVIEW_IMAGE_REQUIRED_ERROR)
    private MultipartFile overviewImage;

    @NotNull(message = HOTEL_IMAGES_REQUIRED_ERROR)
    private List<MultipartFile> images;

}
