package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.api.hotel_booking_service.dto.admin.CreateAdminDto;
import spring.api.hotel_booking_service.entity.Admin;
import spring.api.hotel_booking_service.repository.AdminRepository;
import spring.api.hotel_booking_service.service.AdminService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    AdminRepository adminRepository;

    @Override
    @Transactional
    public void createAdmin(CreateAdminDto createAdminDto) {
        adminRepository.save(Admin.builder()
                .email(createAdminDto.getEmail())
                .password(createAdminDto.getPassword())
                .build());
    }

}
