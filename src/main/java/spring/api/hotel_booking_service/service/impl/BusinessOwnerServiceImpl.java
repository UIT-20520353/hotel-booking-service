package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.dto.BusinessOwnerDto;
import spring.api.hotel_booking_service.dto.register.BusinessOwnerRegister;
import spring.api.hotel_booking_service.entity.BusinessOwner;
import spring.api.hotel_booking_service.entity.User;
import spring.api.hotel_booking_service.entity.filter.BusinessOwnerFilter;
import spring.api.hotel_booking_service.helper.enumeration.BusinessOwnerStatus;
import spring.api.hotel_booking_service.helper.enumeration.UserRole;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.helper.mapper.BusinessOwnerMapper;
import spring.api.hotel_booking_service.helper.specification.BusinessOwnerSpecification;
import spring.api.hotel_booking_service.helper.util.FileUtils;
import spring.api.hotel_booking_service.repository.BusinessOwnerRepository;
import spring.api.hotel_booking_service.service.BusinessOwnerService;
import spring.api.hotel_booking_service.service.ImageService;
import spring.api.hotel_booking_service.service.UserService;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessOwnerServiceImpl implements BusinessOwnerService {

    UserService userService;
    BCryptPasswordEncoder passwordEncoder;
    ImageService imageService;
    BusinessOwnerRepository businessOwnerRepository;
    BusinessOwnerMapper businessOwnerMapper;

    @Override
    @Transactional
    public void createBusinessOwner(BusinessOwnerRegister requestDto) {
        if (userService.isEmailExist(requestDto.getEmail())) {
            throw new BadRequestException(USER_EMAIL_EXISTED);
        }

        MultipartFile frontIdentityCard = requestDto.getFrontIdentityCard();
        MultipartFile backIdentityCard = requestDto.getBackIdentityCard();
        MultipartFile selfieImage = requestDto.getSelfieImage();

        if (!FileUtils.isValidFile(frontIdentityCard)) {
            throw new BadRequestException(FRONT_IDENTITY_CARD_INVALID);
        }
        if (!FileUtils.isValidFile(backIdentityCard)) {
            throw new BadRequestException(BACK_IDENTITY_CARD_INVALID);
        }
        if (!FileUtils.isValidFile(selfieImage)) {
            throw new BadRequestException(SELFIE_IMAGE_INVALID);
        }

        String frontIdentityCardUrl = imageService.uploadImage(frontIdentityCard);
        String backIdentityCardUrl = imageService.uploadImage(backIdentityCard);
        String selfieImageUrl = imageService.uploadImage(selfieImage);

        User user = userService.save(User.builder()
                                            .email(requestDto.getEmail())
                                            .password(passwordEncoder.encode(requestDto.getPassword()))
                                            .role(UserRole.BUSINESS_OWNER)
                                            .build());

        BusinessOwner businessOwner = BusinessOwner.builder()
                                                    .user(user)
                                                    .firstName(requestDto.getFirstName())
                                                    .lastName(requestDto.getLastName())
                                                    .businessName(requestDto.getBusinessName())
                                                    .phoneNumber(requestDto.getPhoneNumber())
                                                    .status(BusinessOwnerStatus.PENDING)
                                                    .address(requestDto.getAddress())
                                                    .businessId(requestDto.getBusinessId())
                                                    .frontIdentityCard(frontIdentityCardUrl)
                                                    .backIdentityCard(backIdentityCardUrl)
                                                    .selfieImage(selfieImageUrl)
                                                    .build();

        businessOwnerRepository.save(businessOwner);
    }

    @Override
    public BusinessOwner findBusinessOwnerByEmail(String email) {
        return businessOwnerRepository.findByUserEmail(email)
                                      .orElseThrow(() -> new BadRequestException(BUSINESS_OWNER_NOT_FOUND));
    }

    @Override
    public Page<BusinessOwnerDto> getBusinessOwners(BusinessOwnerFilter filter, Pageable pageable) {
        Specification<BusinessOwner> specification = createSpecification(filter);
        return businessOwnerRepository.findAll(specification, pageable)
                                      .map(businessOwnerMapper::toBusinessOwnerDto);
    }

    @Override
    public BusinessOwnerDto getBusinessOwner(Long businessOwnerId) {
        return businessOwnerRepository.findById(businessOwnerId)
                                      .map(businessOwnerMapper::toBusinessOwnerDto)
                                      .orElseThrow(() -> new BadRequestException(BUSINESS_OWNER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void approveBusinessOwner(Long businessOwnerId, boolean status) {
        BusinessOwner businessOwner = businessOwnerRepository.findById(businessOwnerId)
                                                             .orElseThrow(() -> new BadRequestException(BUSINESS_OWNER_NOT_FOUND));

        if (businessOwner.getStatus().equals(BusinessOwnerStatus.PENDING)) {
            if (status) {
                businessOwner.setStatus(BusinessOwnerStatus.APPROVED);
            } else {
                businessOwner.setStatus(BusinessOwnerStatus.REJECTED);
            }
        } else {
            if (businessOwner.getStatus().equals(BusinessOwnerStatus.APPROVED)) {
                throw new BadRequestException(BUSINESS_OWNER_APPROVED);
            } else {
                throw new BadRequestException(BUSINESS_OWNER_REJECTED);
            }
        }

        businessOwnerRepository.save(businessOwner);
    }

    @Override
    public BusinessOwner findBusinessOwnerByUserId(Long userId) {
        return businessOwnerRepository.findByUserId(userId)
                                      .orElseThrow(() -> new BadRequestException(BUSINESS_OWNER_NOT_FOUND));
    }

    private Specification<BusinessOwner> createSpecification(BusinessOwnerFilter filter) {
        Specification<BusinessOwner> specification = Specification.where(null);

        if (filter == null) {
            return specification;
        }

        if (filter.getName() != null) {
            specification = specification.and(BusinessOwnerSpecification.nameContains(filter.getName()));
        }
        if (filter.getEmail() != null) {
            specification = specification.and(BusinessOwnerSpecification.emailContains(filter.getEmail()));
        }

        return specification;
    }

}
