package com.service.hotel_booking.services.implement;

import com.service.hotel_booking.config.jwt.SecurityUtils;
import com.service.hotel_booking.entities.*;
import com.service.hotel_booking.entities.request.CreateBookingDto;
import com.service.hotel_booking.entities.response.booking.BookingDetailDto;
import com.service.hotel_booking.enumerations.BookingStatus;
import com.service.hotel_booking.exceptions.BadRequestException;
import com.service.hotel_booking.exceptions.InternalException;
import com.service.hotel_booking.mappers.BookingMapper;
import com.service.hotel_booking.repositories.BookingRepository;
import com.service.hotel_booking.repositories.BookingRoomRepository;
import com.service.hotel_booking.services.BookingService;
import com.service.hotel_booking.services.PropertyService;
import com.service.hotel_booking.services.RoomService;
import com.service.hotel_booking.services.UserService;
import com.service.hotel_booking.services.criteria.BookingCriteria;
import com.service.hotel_booking.services.query.QueryService;
import com.service.hotel_booking.services.query.builder.LongFilterBuilder;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static com.service.hotel_booking.constant.MessageConstant.BOOKING_NOT_EXIST;
import static com.service.hotel_booking.constant.MessageConstant.ROOM_NOT_EXIST;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl extends QueryService<Booking> implements BookingService {

    BookingRoomRepository bookingRoomRepository;
    BookingRepository bookingRepository;
    UserService userService;
    RoomService roomService;
    PropertyService propertyService;
    BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingDetailDto createBooking(CreateBookingDto body) {
        User user = userService.getUserById(SecurityUtils.getCurrentUserId());
        Property property = propertyService.getPropertyEntityById(body.getPropertyId());
        List<Room> rooms = roomService.getRoomEntitiesByIdsAndPropertyId(property.getId(), body.getRoomIds());

        if (rooms.isEmpty()) {
            throw new BadRequestException(ROOM_NOT_EXIST);
        }

        long totalPrice = rooms.stream().map(Room::getPrice)
                                        .reduce(Long::sum)
                                        .orElseThrow(InternalException::new);
        long depositMoney = 0L;

        if (property.getDepositPercent() > 0) {
            depositMoney = totalPrice * property.getDepositPercent() / 100;
        }

        Booking booking = bookingRepository.save(Booking.builder()
                                                        .status(property.getDepositPercent() > 0 ?
                                                                        BookingStatus.PENDING :
                                                                        BookingStatus.CONFIRMED)
                                                        .totalPrice(totalPrice)
                                                        .startDate(body.getStartDate())
                                                        .endDate(body.getEndDate())
                                                        .user(user)
                                                        .property(property)
                                                        .createAt(Instant.now())
                                                        .build());


        rooms.forEach(room -> {
            this.createBookingRoom(booking, room);
        });

        return bookingMapper.toBookingDetailDto(booking, depositMoney, property, rooms);
    }

    @Override
    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking booking = getBookingEntityById(bookingId);
        if (!Objects.equals(booking.getUser().getId(), SecurityUtils.getCurrentUserId())) {
            throw new BadRequestException(BOOKING_NOT_EXIST);
        }
        booking.setStatus(BookingStatus.CANCELLED);
    }

    @Override
    public Booking getBookingEntityById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new BadRequestException(BOOKING_NOT_EXIST));
    }

    @Override
    public Page<BookingDetailDto> getAllBookings(BookingCriteria criteria, Pageable pageable) {
        Specification<Booking> specification = createSpecification(criteria);
        return bookingRepository.findAll(specification, pageable).map(bookingMapper::toBookingDetailDto);
    }

    @Transactional
    public void createBookingRoom(Booking booking, Room room) {
        bookingRoomRepository.save(BookingRoom.builder()
                                              .room(room)
                                              .booking(booking)
                                              .build());
    }

    private Specification<Booking> createSpecification(BookingCriteria criteria) {
        Specification<Booking> specification = Specification.where(null);

        specification = specification.and(buildSpecification(LongFilterBuilder.builder().equals(SecurityUtils.getCurrentUserId()).build(),
                                                             root -> root.get(Booking_.USER).get(User_.ID)));

        if (criteria != null) {
            if (Objects.nonNull(criteria.getStartDate())) {
                specification = specification.and(buildSpecification(criteria.getStartDate(), Booking_.startDate));
            }

            if (Objects.nonNull(criteria.getEndDate())) {
                specification = specification.and(buildSpecification(criteria.getEndDate(), Booking_.endDate));
            }

            if (Objects.nonNull(criteria.getStatus())) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Booking_.status));
            }
        }

        return specification;
    }

}
