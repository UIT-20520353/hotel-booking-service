package spring.api.hotel_booking_service.dto.attraction;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Data
public class CreateAttractionDto {

    @NotBlank(message = ATTRACTION_NAME_REQUIRED_ERROR)
    @Max(value = 255, message = ATTRACTION_NAME_MAX_LENGTH_ERROR)
    private String name;

    @NotBlank(message = ATTRACTION_DESCRIPTION_REQUIRED_ERROR)
    private String description;

    @NotBlank(message = ATTRACTION_ADDRESS_REQUIRED_ERROR)
    private String address;

    @NotNull(message = ATTRACTION_OVERVIEW_IMAGE_REQUIRED_ERROR)
    private MultipartFile overviewImage;

    @NotBlank(message = ATTRACTION_SUMMARY_REQUIRED_ERROR)
    private String summary;

    @NotNull(message = ATTRACTION_LATITUDE_REQUIRED_ERROR)
    private Double latitude;

    @NotNull(message = ATTRACTION_LONGITUDE_REQUIRED_ERROR)
    private Double longitude;

}
