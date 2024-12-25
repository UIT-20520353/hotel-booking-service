package spring.api.hotel_booking_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.api.hotel_booking_service.dto.attraction.AttractionDto;
import spring.api.hotel_booking_service.dto.attraction.CreateAttractionDto;
import spring.api.hotel_booking_service.entity.Attraction;

public interface AttractionService {

    void createAttraction(CreateAttractionDto createAttractionDto);
    Page<AttractionDto> getAttractions(Pageable pageable);
    void deleteAttraction(Long attractionId);
    Attraction getAttraction(Long attractionId);

}
