package spring.api.hotel_booking_service.dto;

public record AdministrativeRegionDto(
        Integer id,
        String name,
        String nameEN,
        String codeName,
        String codeNameEN
) {
}
