package spring.api.hotel_booking_service.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import spring.api.hotel_booking_service.helper.enumeration.UserGender;

import java.time.Instant;

import static spring.api.hotel_booking_service.helper.constant.Message.*;
import static spring.api.hotel_booking_service.helper.constant.Message.LOGIN_TYPE_REQUIRED_ERROR;

@Data
public class UserRegisterDto {

    @NotBlank(message = FIRST_NAME_REQUIRED_ERROR)
    private String firstName;

    @NotBlank(message = LAST_NAME_REQUIRED_ERROR)
    private String lastName;

    @NotBlank(message = EMAIL_REQUIRED_ERROR)
    @Email(message = EMAIL_FORMAT_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED_ERROR)
    private String password;

    @NotNull(message = DATE_OF_BIRTH_REQUIRED_ERROR)
    private Instant dateOfBirth;

    @NotNull(message = GENDER_REQUIRED_ERROR)
    private UserGender gender;

}
