package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class AuthLoginRequest {

    @NotBlank(message = EMAIL_REQUIRED_ERROR)
    @Email(message = EMAIL_FORMAT_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED_ERROR)
    private String password;

}
