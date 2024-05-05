package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArgentRegisterRequest extends AuthRegisterRequest {

    @NotBlank(message = IDENTITY_NUMBER_REQUIRED_ERROR)
    @Length(max = 20, message = IDENTITY_NUMBER_MAX_LENGTH_ERROR)
    @Length(min = 9, message = IDENTITY_NUMBER_MIN_LENGTH_ERROR)
    private String identityNumber;

//    private MultipartFile frontIdentityCard;
//
//    private MultipartFile backIdentityCard;
//
//    private MultipartFile selfieImg;

}
