package spring.api.hotel_booking_service.helper.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.entity.District;
import spring.api.hotel_booking_service.dto.DistrictDto;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DistrictMapper {

    AdministrativeUnitMapper administrativeUnitMapper;

    public DistrictDto toDistrictDto(District district) {
        return new DistrictDto(
                district.getCode(),
                district.getName(),
                district.getNameEN(),
                district.getFullName(),
                district.getFullNameEN(),
                district.getCodeName(),
                administrativeUnitMapper.toAdministrativeUnitDto(district.getAdministrativeUnit())
        );
    }

}
