package com.service.hotel_booking.controllers;

import com.service.hotel_booking.entities.request.CreateRoomDto;
import com.service.hotel_booking.entities.response.RoomDto;
import com.service.hotel_booking.services.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Room Resources")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoomController {

    RoomService roomService;

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Void> createRoom(@ModelAttribute @Valid CreateRoomDto body) {
        roomService.createRoom(body);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<List<RoomDto>> getRoomList(@PathVariable Long propertyId) {
        return ResponseEntity.ok(roomService.getAllRooms(propertyId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

}
