package spring.api.hotel_booking_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.api.hotel_booking_service.dto.BusinessOwnerDto;
import spring.api.hotel_booking_service.dto.register.BusinessOwnerRegister;
import spring.api.hotel_booking_service.entity.BusinessOwner;
import spring.api.hotel_booking_service.entity.filter.BusinessOwnerFilter;

public interface BusinessOwnerService {

    void createBusinessOwner(BusinessOwnerRegister requestDto);
    BusinessOwner findBusinessOwnerByEmail(String email);
    Page<BusinessOwnerDto> getBusinessOwners(BusinessOwnerFilter filter, Pageable pageable);
    BusinessOwnerDto getBusinessOwner(Long businessOwnerId);
    void approveBusinessOwner(Long businessOwnerId, boolean status);
    BusinessOwner findBusinessOwnerByUserId(Long userId);

}
