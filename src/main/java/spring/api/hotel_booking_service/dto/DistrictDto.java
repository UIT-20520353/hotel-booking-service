package spring.api.hotel_booking_service.dto;

public record DistrictDto(
        String code,
        String name,
        String nameEN,
        String fullName,
        String fullNameEN,
        String codeName,
        AdministrativeUnitDto administrativeUnit
) {
}
