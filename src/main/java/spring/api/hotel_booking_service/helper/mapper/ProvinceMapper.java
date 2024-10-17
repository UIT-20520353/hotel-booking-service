package spring.api.hotel_booking_service.helper.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.ProvinceDto;
import spring.api.hotel_booking_service.entity.Province;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProvinceMapper {

    AdministrativeRegionMapper administrativeRegionMapper;
    AdministrativeUnitMapper administrativeUnitMapper;

    public ProvinceDto toProvinceDto(Province province) {
        return new ProvinceDto(
                province.getCode(),
                province.getName(),
                province.getNameEN(),
                province.getFullName(),
                province.getFullNameEN(),
                province.getCodeName(),
                administrativeRegionMapper.toAdministrativeRegionDto(province.getAdministrativeRegion()),
                administrativeUnitMapper.toAdministrativeUnitDto(province.getAdministrativeUnit())
        );
    }

}
