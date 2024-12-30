package spring.api.hotel_booking_service.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Data
public class BusinessOwnerRegister {

    @NotBlank(message = EMAIL_REQUIRED_ERROR)
    @Email(message = EMAIL_FORMAT_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED_ERROR)
    private String password;

    @NotBlank(message = FIRST_NAME_REQUIRED_ERROR)
    private String firstName;

    @NotBlank(message = LAST_NAME_REQUIRED_ERROR)
    private String lastName;

    @NotBlank(message = BUSINESS_NAME_REQUIRED_ERROR)
    private String businessName;

    @NotBlank(message = PHONE_NUMBER_REQUIRED_ERROR)
    private String phoneNumber;

    @NotBlank(message = ADDRESS_REQUIRED_ERROR)
    private String address;

    @NotBlank(message = BUSINESS_ID_REQUIRED_ERROR)
    private String businessId;

    @NotNull(message = FRONT_IDENTITY_CARD_REQUIRED_ERROR)
    private MultipartFile frontIdentityCard;

    @NotNull(message = BACK_IDENTITY_CARD_REQUIRED_ERROR)
    private MultipartFile backIdentityCard;

    @NotNull(message = SELFIE_IMAGE_REQUIRED_ERROR)
    private MultipartFile selfieImage;

}
