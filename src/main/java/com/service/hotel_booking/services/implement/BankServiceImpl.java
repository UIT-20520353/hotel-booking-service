package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.Argent_;
import com.service.hotel_booking.entities.Bank;
import com.service.hotel_booking.entities.Bank_;
import com.service.hotel_booking.entities.User;
import com.service.hotel_booking.entities.request.CreateBankDto;
import com.service.hotel_booking.entities.response.BankDetailDto;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.BankMapper;
import com.service.hotel_booking.repositories.BankRepository;
import com.service.hotel_booking.services.BankService;
import com.service.hotel_booking.services.ResourceService;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.criteria.BankCriteria;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.services.query.builder.LongFilterBuilder;
import com.service.hotel_booking.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.BANK_NOT_FOUND;
import static com.service.hotel_booking.constant.MessageConstant.BANK_QR_CODE_REQUIRED_ERROR;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankServiceImpl extends QueryService<Bank> implements BankService {

    BankRepository bankRepository;
    ResourceService resourceService;
    BankMapper bankMapper;
    UserService userService;

    @Override
    @Transactional
    public BankDetailDto createBank(Long userId, CreateBankDto body) {
        User user = userService.getUserById(userId);
        FileUtils.checkMultipartFileNull(body.getQrCode(), BANK_QR_CODE_REQUIRED_ERROR);
        String qrUrl = resourceService.uploadBankQr(body.getQrCode());
        Bank bank = bankRepository.save(Bank.builder()
                                            .bankName(body.getBankName())
                                            .accountNumber(body.getAccountNumber())
                                            .accountName(body.getAccountName())
                                            .isDefault(body.isDefault())
                                            .createAt(Instant.now())
                                            .qrCode(qrUrl)
                                            .argent(user.getArgent())
                                            .build());

        return bankMapper.toBankDetailDto(bank);
    }

    @Override
    public Page<BankDetailDto> getAllBanks(BankCriteria criteria, Pageable pageable) {
        User user = userService.getUserById(SecurityUtils.getCurrentUserId());
        Specification<Bank> specification = createSpecification(criteria, user.getArgent().getId());
        return bankRepository.findAll(specification, pageable).map(bankMapper::toBankDetailDto);
    }

    @Override
    @Transactional
    public void deleteBank(Long bankId) {
        User user = userService.getUserById(SecurityUtils.getCurrentUserId());
        Bank bank = bankRepository.findByIdAndArgentId(bankId, user.getArgent().getId())
                                  .orElseThrow(() -> new BadRequestException(BANK_NOT_FOUND));
        bankRepository.delete(bank);
    }

    private Specification<Bank> createSpecification(BankCriteria criteria, Long argentId) {
        Specification<Bank> specification = Specification.where(null);

        specification = specification.and(buildSpecification(LongFilterBuilder.builder().equals(argentId).build(),
                                                             root -> root.get(Bank_.argent).get(Argent_.id)));

        if (criteria != null) {
            if (Objects.nonNull(criteria.getBankName())) {
                specification =
                        specification.and(buildSpecification(criteria.getBankName(),
                                                             root -> root.get(Bank_.bankName)));
            }
        }

        return specification;
    }

}
