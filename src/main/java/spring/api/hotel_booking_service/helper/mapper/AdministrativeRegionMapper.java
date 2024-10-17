package spring.api.hotel_booking_service.helper.mapper;

import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.AdministrativeRegionDto;
import spring.api.hotel_booking_service.entity.AdministrativeRegion;

@Component
public class AdministrativeRegionMapper {

    public AdministrativeRegionDto toAdministrativeRegionDto(AdministrativeRegion administrativeRegion) {
        return new AdministrativeRegionDto(
                administrativeRegion.getId(),
                administrativeRegion.getName(),
                administrativeRegion.getNameEN(),
                administrativeRegion.getCodeName(),
                administrativeRegion.getCodeNameEN()
        );
    }

}
