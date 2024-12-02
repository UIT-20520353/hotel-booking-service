package spring.api.hotel_booking_service.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Data
public class RequestDto {

    @NotBlank(message = EMAIL_REQUIRED_ERROR)
    @Email(message = EMAIL_FORMAT_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED_ERROR)
    private String password;

}
