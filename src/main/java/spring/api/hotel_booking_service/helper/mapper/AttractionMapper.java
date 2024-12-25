package spring.api.hotel_booking_service.helper.mapper;

import org.springframework.stereotype.Component;
import spring.api.hotel_booking_service.dto.attraction.AttractionDto;
import spring.api.hotel_booking_service.entity.Attraction;

@Component
public class AttractionMapper {

    public AttractionDto toAttractionDto(Attraction attraction) {
        return new AttractionDto(
                attraction.getId(),
                attraction.getName(),
                attraction.getDescription(),
                attraction.getAddress(),
                attraction.getOverviewImage(),
                attraction.getSummary(),
                attraction.getLatitude(),
                attraction.getLongitude()
        );
    }

}
