package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.admin.AdminProfileDto;
import spring.api.hotel_booking_service.dto.admin.CreateAdminDto;

public interface SystemAdminService {

    void createAdmin(CreateAdminDto createAdminDto);
    AdminProfileDto getProfile();

}
