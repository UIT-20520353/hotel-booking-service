package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.dto.AdministrativeUnitDto;
import spring.api.hotel_booking_service.helper.mapper.AdministrativeUnitMapper;
import spring.api.hotel_booking_service.repository.AdministrativeUnitRepository;
import spring.api.hotel_booking_service.service.AdministrativeUnitService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService {

    AdministrativeUnitRepository administrativeUnitRepository;
    AdministrativeUnitMapper administrativeUnitMapper;

    @Override
    public List<AdministrativeUnitDto> getAllAdministrativeUnits() {
        return administrativeUnitRepository
                .findAll()
                .stream()
                .map(administrativeUnitMapper::toAdministrativeUnitDto)
                .toList();
    }

}
