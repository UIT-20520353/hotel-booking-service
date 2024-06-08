package com.service.hotel_booking.entities.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Data
public class CreateBankDto {

    @NotBlank(message = BANK_NAME_REQUIRED_ERROR)
    private String bankName;

    @NotBlank(message = ACCOUNT_NUMBER_REQUIRED_ERROR)
    private String accountNumber;

    @NotBlank(message = ACCOUNT_NAME_REQUIRED_ERROR)
    private String accountName;

    @NotNull(message = BANK_QR_CODE_REQUIRED_ERROR)
    private MultipartFile qrCode;

    private boolean isDefault;

}
