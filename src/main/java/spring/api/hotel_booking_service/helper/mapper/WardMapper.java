package spring.api.hotel_booking_service.helper.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.WardDto;
import spring.api.hotel_booking_service.entity.Ward;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WardMapper {

    AdministrativeUnitMapper administrativeUnitMapper;

    public WardDto toWardDto(Ward ward) {
        return new WardDto(
                ward.getCode(),
                ward.getName(),
                ward.getNameEN(),
                ward.getFullName(),
                ward.getFullNameEN(),
                ward.getCodeName(),
                administrativeUnitMapper.toAdministrativeUnitDto(ward.getAdministrativeUnit())
        );
    }

}
