package spring.api.hotel_booking_service.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import spring.api.hotel_booking_service.entity.AdministrativeUnit;
import spring.api.hotel_booking_service.repository.AdministrativeUnitRepository;
import spring.api.hotel_booking_service.service.AdministrativeUnitService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService {

    AdministrativeUnitRepository administrativeUnitRepository;

    @Override
    public List<AdministrativeUnit> getAllAdministrativeUnits() {
        return administrativeUnitRepository.findAll();
    }

}
