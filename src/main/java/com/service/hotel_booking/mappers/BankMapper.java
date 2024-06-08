package com.service.hotel_booking.mappers;

import com.service.hotel_booking.entities.Bank;
import com.service.hotel_booking.entities.response.BankDetailDto;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public BankDetailDto toBankDetailDto(Bank bank) {
        return new BankDetailDto(
                bank.getId(),
                bank.getBankName(),
                bank.getAccountNumber(),
                bank.getAccountName(),
                bank.getQrCode(),
                bank.isDefault(),
                bank.getCreateAt()
        );
    }

}
