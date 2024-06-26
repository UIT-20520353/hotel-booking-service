package com.service.hotel_booking.repositories;

import com.service.hotel_booking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    List<Room> findAllByPropertyId(Long id);
    Optional<Room> findAllByPropertyIdAndId(Long propertyId, Long id);

}
