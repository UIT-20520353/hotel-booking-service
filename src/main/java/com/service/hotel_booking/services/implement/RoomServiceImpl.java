package com.service.hotel_booking.services.implement;


import com.service.hotel_booking.entities.Amenity;
import com.service.hotel_booking.entities.Property;
import com.service.hotel_booking.entities.Room;
import com.service.hotel_booking.entities.Room_;
import com.service.hotel_booking.entities.request.CreateRoomDto;
import com.service.hotel_booking.entities.response.RoomDto;
import com.service.hotel_booking.enumerations.AmenityType;
import com.service.hotel_booking.enumerations.PropertyStatus;
import com.service.hotel_booking.enumerations.RoomStatus;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.mappers.RoomMapper;
import com.service.hotel_booking.repositories.RoomRepository;
import com.service.hotel_booking.services.*;
import com.service.hotel_booking.services.criteria.AmenityCriteria;
import com.service.hotel_booking.services.criteria.RoomCriteria;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.services.query.builder.AmenityTypeFilterBuilder;
import com.service.hotel_booking.services.query.builder.BooleanFilterBuilder;
import com.service.hotel_booking.services.query.builder.LongFilterBuilder;
import com.service.hotel_booking.services.query.builder.RoomStatusFilterBuilder;
import com.service.hotel_booking.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomServiceImpl extends QueryService<Room> implements RoomService {

    RoomRepository roomRepository;
    PropertyService propertyService;
    AmenityService amenityService;
    RoomAmenityService roomAmenityService;
    ResourceService resourceService;
    RoomImageService roomImageService;
    RoomMapper roomMapper;

    @Override
    @Transactional
    public void createRoom(CreateRoomDto body) {
        Property property = propertyService.getPropertyEntityById(body.getPropertyId());
        if (!property.getStatus().equals(PropertyStatus.AVAILABLE)) {
            throw new BadRequestException(PROPERTY_NOT_AVAILABLE);
        }

        Room room = Room.builder()
                        .name(body.getName())
                        .price(body.getPrice())
                        .property(property)
                        .status(RoomStatus.AVAILABLE)
                        .build();

        List<Pair<byte[], String>> imgDataList = body.getImgList().stream()
                                                     .map(img -> {
                                                         byte[] bytes = FileUtils.checkFile(img);
                                                         return new Pair<>(bytes, img.getContentType());
                                                     })
                                                     .toList();

        roomRepository.save(room);

        imgDataList.forEach(item -> {
            String url = resourceService.uploadRoomImage(item.a, item.b);
            roomImageService.createRoomImage(room, url);
        });

        AmenityCriteria criteria = AmenityCriteria
                .builder()
                .id(LongFilterBuilder.builder().in(body.getAmenityIds()).build())
                .type(AmenityTypeFilterBuilder.builder().equals(AmenityType.ROOM).build())
                .isDeleted(BooleanFilterBuilder.builder().equals(false).build())
                .build();
        List<Amenity> amenities = amenityService.getListAmenityEntity(criteria);
        amenities.forEach(amenity -> {
            roomAmenityService.createRoomAmenity(room, amenity);
        });
    }

    @Override
    public List<RoomDto> getAllRooms(Long propertyId) {
        RoomCriteria criteria = RoomCriteria.builder()
                                            .status(RoomStatusFilterBuilder.builder().notEquals(RoomStatus.DELETED).build())
                                            .build();
        return roomRepository.findAllByPropertyId(propertyId).stream().map(roomMapper::toRoomDto).toList();
    }

    @Override
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = this.getRoomEntityById(roomId);
        room.setStatus(RoomStatus.DELETED);
    }

    @Override
    public Room getRoomEntityById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new BadRequestException(ROOM_NOT_EXIST));
    }

    private Specification<Room> createSpecification(RoomCriteria criteria) {
        Specification<Room> specification = Specification.where(null);

        if (criteria != null) {
            if (Objects.nonNull(criteria.getStatus())) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Room_.status));
            }
        }

        return specification;
    }

}
