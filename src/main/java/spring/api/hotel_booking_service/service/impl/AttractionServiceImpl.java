package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.dto.attraction.AttractionDto;
import spring.api.hotel_booking_service.dto.attraction.CreateAttractionDto;
import spring.api.hotel_booking_service.entity.Attraction;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.helper.mapper.AttractionMapper;
import spring.api.hotel_booking_service.helper.util.FileUtils;
import spring.api.hotel_booking_service.repository.AttractionRepository;
import spring.api.hotel_booking_service.service.AttractionService;
import spring.api.hotel_booking_service.service.ImageService;

import java.math.BigDecimal;

import static spring.api.hotel_booking_service.helper.constant.Message.ATTRACTION_NOT_FOUND;
import static spring.api.hotel_booking_service.helper.constant.Message.ATTRACTION_OVERVIEW_IMAGE_INVALID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    AttractionRepository attractionRepository;
    ImageService imageService;
    AttractionMapper attractionMapper;

    @Override
    @Transactional
    public void createAttraction(CreateAttractionDto createAttractionDto) {
        MultipartFile overviewImage = createAttractionDto.getOverviewImage();
        BigDecimal latitude = createAttractionDto.getLatitude();
        BigDecimal longitude = createAttractionDto.getLongitude();

        if (!FileUtils.isValidFile(overviewImage)) {
            throw new BadRequestException(ATTRACTION_OVERVIEW_IMAGE_INVALID);
        }

        String imageUrl = imageService.uploadImage(overviewImage);

        Attraction attraction = Attraction.builder()
                                          .name(createAttractionDto.getName())
                                          .description(createAttractionDto.getDescription())
                                          .address(createAttractionDto.getAddress())
                                          .overviewImage(imageUrl)
                                          .summary(createAttractionDto.getSummary())
                                          .latitude(latitude)
                                          .longitude(longitude)
                                          .build();

        attractionRepository.save(attraction);
    }

    @Override
    public Page<AttractionDto> getAttractions(Pageable pageable) {
        return attractionRepository.findAll(pageable)
                                   .map(attractionMapper::toAttractionDto);
    }

    @Override
    @Transactional
    public void deleteAttraction(Long attractionId) {
        attractionRepository.deleteById(attractionId);
    }

    @Override
    public Attraction getAttraction(Long attractionId) {
        return attractionRepository.findById(attractionId)
                                   .orElseThrow(() -> new BadRequestException(ATTRACTION_NOT_FOUND));
    }

}
