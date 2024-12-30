package spring.api.hotel_booking_service.helper.mapper;

import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.BusinessOwnerDto;
import spring.api.hotel_booking_service.entity.BusinessOwner;

@Component
public class BusinessOwnerMapper {

    public BusinessOwnerDto toBusinessOwnerDto(BusinessOwner businessOwner) {
        return new BusinessOwnerDto(
                businessOwner.getId(),
                businessOwner.getUser().getEmail(),
                businessOwner.getFirstName(),
                businessOwner.getLastName(),
                businessOwner.getPhoneNumber(),
                businessOwner.getBusinessName(),
                businessOwner.getStatus(),
                businessOwner.getAddress(),
                businessOwner.getBusinessId(),
                businessOwner.getFrontIdentityCard(),
                businessOwner.getBackIdentityCard(),
                businessOwner.getSelfieImage()
        );
    }

}
