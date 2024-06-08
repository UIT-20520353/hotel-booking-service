package com.service.hotel_booking.controllers;

import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.request.CreateBankDto;
import com.service.hotel_booking.entities.response.BankDetailDto;
import com.service.hotel_booking.services.BankService;
import com.service.hotel_booking.services.criteria.BankCriteria;
import com.service.hotel_booking.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value ="/api/banks")
@Tag(name = "Bank Resources")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankController {

    BankService bankService;

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<BankDetailDto> createBank(@ModelAttribute @Valid CreateBankDto body) {
        return ResponseEntity.ok(bankService.createBank(SecurityUtils.getCurrentUserId(), body));
    }

    @GetMapping
    public ResponseEntity<List<BankDetailDto>> getBank(BankCriteria criteria,
                                                       @ParameterObject @PageableDefault Pageable pageable) {
        final Page<BankDetailDto> page = bankService.getAllBanks(criteria, pageable);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long bankId) {
        bankService.deleteBank(bankId);
        return ResponseEntity.noContent().build();
    }

}
