package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.entity.AdministrativeRegion;
import spring.api.hotel_booking_service.repository.AdministrativeRegionRepository;
import spring.api.hotel_booking_service.service.AdministrativeRegionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdministrativeRegionServiceImpl implements AdministrativeRegionService {

    AdministrativeRegionRepository administrativeRegionRepository;

    @Override
    public List<AdministrativeRegion> getAllRegions() {
        return administrativeRegionRepository.findAll();
    }
}
