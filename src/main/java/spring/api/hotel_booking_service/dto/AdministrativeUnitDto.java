package spring.api.hotel_booking_service.dto;

public record AdministrativeUnitDto(
        Integer id,
        String fullName,
        String fullNameEN,
        String shortName,
        String shortNameEN,
        String codeName,
        String codeNameEN
) {
}
