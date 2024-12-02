package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.AdministrativeRegionDto;
import spring.api.hotel_booking_service.helper.mapper.AdministrativeRegionMapper;
import spring.api.hotel_booking_service.repository.AdministrativeRegionRepository;
import spring.api.hotel_booking_service.service.AdministrativeRegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdministrativeRegionServiceImpl implements AdministrativeRegionService {

    AdministrativeRegionRepository administrativeRegionRepository;
    AdministrativeRegionMapper administrativeRegionMapper;

    @Override
    public List<AdministrativeRegionDto> getAllRegions() {
        return administrativeRegionRepository
                .findAll()
                .stream()
                .map(administrativeRegionMapper::toAdministrativeRegionDto)
                .toList();
    }
}
