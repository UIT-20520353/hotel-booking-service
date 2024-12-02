package spring.api.hotel_booking_service.service;

import spring.api.hotel_booking_service.dto.AdministrativeRegionDto;

import java.util.List;

public interface AdministrativeRegionService {

    List<AdministrativeRegionDto> getAllRegions();

}
