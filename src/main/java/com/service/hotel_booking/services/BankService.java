package com.service.hotel_booking.services;

import com.service.hotel_booking.entities.request.CreateBankDto;
import com.service.hotel_booking.entities.response.BankDetailDto;
import com.service.hotel_booking.services.criteria.BankCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {

    BankDetailDto createBank(Long userId, CreateBankDto body);
    Page<BankDetailDto> getAllBanks(BankCriteria criteria, Pageable pageable);
    void deleteBank(Long bankId);

}
