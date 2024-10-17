package spring.api.hotel_booking_service.dto;

public record ProvinceDto(
        String code,
        String name,
        String nameEN,
        String fullName,
        String fullNameEN,
        String codeName,
        AdministrativeRegionDto administrativeRegion,
        AdministrativeUnitDto administrativeUnit
) {
}
