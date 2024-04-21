package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class AuthRegisterRequest {

    @NotBlank(message = FIRST_NAME_REQUIRED_ERROR)
    @Length(max = 100, message = FIRST_NAME_MAX_LENGTH_ERROR)
    private String firstName;

    @NotBlank(message = LAST_NAME_REQUIRED_ERROR)
    @Length(max = 100, message = LAST_NAME_MAX_LENGTH_ERROR)
    private String lastName;

    @NotBlank(message = PHONE_NUMBER_REQUIRED_ERROR)
    @Length(max = 20, message = PHONE_NUMBER_MAX_LENGTH_ERROR)
    private String phoneNumber;

    @NotBlank(message = EMAIL_REQUIRED_ERROR)
    @Length(max = 250, message = EMAIL_MAX_LENGTH_ERROR)
    @Email(message = EMAIL_FORMAT_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED_ERROR)
    @Length(max = 300, message = PASSWORD_MAX_LENGTH_ERROR)
    @Length(min = 8, message = PASSWORD_MIN_LENGTH_ERROR)
    private String password;

}
