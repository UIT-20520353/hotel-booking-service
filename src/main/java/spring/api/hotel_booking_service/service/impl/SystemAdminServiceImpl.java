package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.api.hotel_booking_service.config.jwt.SecurityUtils;
import spring.api.hotel_booking_service.dto.admin.AdminProfileDto;
import spring.api.hotel_booking_service.dto.admin.CreateAdminDto;
import spring.api.hotel_booking_service.entity.SystemAdmin;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.repository.SystemAdminRepository;
import spring.api.hotel_booking_service.service.SystemAdminService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SystemAdminServiceImpl implements SystemAdminService {

    SystemAdminRepository systemAdminRepository;

    @Override
    @Transactional
    public void createAdmin(CreateAdminDto createAdminDto) {

    }

    @Override
    public AdminProfileDto getProfile() {
        Long adminId = SecurityUtils.getCurrentUserId();
        throw new BadRequestException(adminId.toString());
    }

}
