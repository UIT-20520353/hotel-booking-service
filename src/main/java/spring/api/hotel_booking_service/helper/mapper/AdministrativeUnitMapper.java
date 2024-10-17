package spring.api.hotel_booking_service.helper.mapper;

import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.AdministrativeUnitDto;
import spring.api.hotel_booking_service.entity.AdministrativeUnit;

@Component
public class AdministrativeUnitMapper {

    public AdministrativeUnitDto toAdministrativeUnitDto(AdministrativeUnit administrativeUnit) {
        return new AdministrativeUnitDto(
                administrativeUnit.getId(),
                administrativeUnit.getFullName(),
                administrativeUnit.getFullNameEN(),
                administrativeUnit.getShortName(),
                administrativeUnit.getShortNameEN(),
                administrativeUnit.getCodeName(),
                administrativeUnit.getCodeNameEN()
        );
    }

}
