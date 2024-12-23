package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.dto.attraction.CreateAttractionDto;
import spring.api.hotel_booking_service.entity.Attraction;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.helper.util.FileUtils;
import spring.api.hotel_booking_service.repository.AttractionRepository;
import spring.api.hotel_booking_service.service.AttractionService;
import spring.api.hotel_booking_service.service.ImageService;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;

import static spring.api.hotel_booking_service.helper.constant.Message.ATTRACTION_OVERVIEW_IMAGE_INVALID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    AttractionRepository attractionRepository;
    ImageService imageService;
    GeometryFactory geometryFactory;

    @Override
    @Transactional
    public void createAttraction(CreateAttractionDto createAttractionDto) {
        MultipartFile overviewImage = createAttractionDto.getOverviewImage();
        Double latitude = createAttractionDto.getLatitude();
        Double longitude = createAttractionDto.getLongitude();

        if (!FileUtils.isValidFile(overviewImage)) {
            throw new BadRequestException(ATTRACTION_OVERVIEW_IMAGE_INVALID);
        }

        Point geom = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        geom.setSRID(4326);

        String imageUrl = imageService.uploadImage(overviewImage);

        Attraction attraction = Attraction.builder()
                                          .name(createAttractionDto.getName())
                                          .description(createAttractionDto.getDescription())
                                          .address(createAttractionDto.getAddress())
                                          .overviewImage(imageUrl)
                                          .summary(createAttractionDto.getSummary())
                                          .latitude(createAttractionDto.getLatitude())
                                          .longitude(createAttractionDto.getLongitude())
                                          .geom(geom)
                                          .build();

        attractionRepository.save(attraction);
    }

}
