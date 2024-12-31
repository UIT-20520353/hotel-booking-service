package spring.api.hotel_booking_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.api.hotel_booking_service.config.jwt.SecurityUtils;
import spring.api.hotel_booking_service.dto.hotel.CreateAmenityDto;
import spring.api.hotel_booking_service.dto.hotel.CreateHotelDto;
import spring.api.hotel_booking_service.entity.BusinessOwner;
import spring.api.hotel_booking_service.entity.Hotel;
import spring.api.hotel_booking_service.entity.HotelAmenity;
import spring.api.hotel_booking_service.entity.HotelImage;
import spring.api.hotel_booking_service.helper.enumeration.HotelImageType;
import spring.api.hotel_booking_service.helper.enumeration.HotelStatus;
import spring.api.hotel_booking_service.helper.exception.BadRequestException;
import spring.api.hotel_booking_service.helper.util.FileUtils;
import spring.api.hotel_booking_service.repository.HotelRepository;
import spring.api.hotel_booking_service.service.*;

import java.util.List;

import static spring.api.hotel_booking_service.helper.constant.Message.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    BusinessOwnerService businessOwnerService;
    ImageService imageService;
    HotelRepository hotelRepository;
    HotelImageService hotelImageService;
    HotelAmenityService hotelAmenityService;

    @Override
    @Transactional
    public void createHotel(CreateHotelDto body) {
        BusinessOwner businessOwner = businessOwnerService.findBusinessOwnerByUserId(SecurityUtils.getCurrentUserId());
        MultipartFile overviewImage = body.getOverviewImage();
        List<MultipartFile> images = body.getImages();

        if (!FileUtils.isValidFile(overviewImage)) {
            throw new BadRequestException(HOTEL_OVERVIEW_IMAGE_INVALID);
        }

        if (images == null || images.stream().anyMatch(image -> !FileUtils.isValidFile(image))) {
            throw new BadRequestException(HOTEL_IMAGE_INVALID);
        }

        String overviewImageUrl = imageService.uploadImage(overviewImage);
        List<String> imageUrls = imageService.uploadImages(images);

        Hotel hotel = hotelRepository.save(Hotel.builder()
                                      .name(body.getName())
                                      .owner(businessOwner)
                                      .address(body.getAddress())
                                      .description(body.getDescription())
                                      .latitude(body.getLatitude())
                                      .longitude(body.getLongitude())
                                      .status(HotelStatus.ACTIVE)
                                      .build());

        imageUrls.forEach(url -> hotelImageService.createHotelImage(HotelImage.builder().hotel(hotel).url(url).type(HotelImageType.DETAIL).build()));
        hotelImageService.createHotelImage(HotelImage.builder().hotel(hotel).url(overviewImageUrl).type(HotelImageType.OVERVIEW).build());
    }

    @Override
    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new BadRequestException(HOTEL_NOT_FOUND));
        hotel.setStatus(HotelStatus.DELETED);
        hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public void addAmenity(Long id, CreateAmenityDto body) {
        Hotel hotel = this.getHotel(id);
        hotelAmenityService.save(HotelAmenity.builder()
                .name(body.getName())
                .unit(body.getUnit())
                .hotel(hotel)
                .price(body.getPrice())
                .build());
    }

    @Override
    public Hotel getHotel(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new BadRequestException(HOTEL_NOT_FOUND));
    }

}
