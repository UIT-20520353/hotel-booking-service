package com.service.hotel_booking.enumerations;

public enum RoomStatus {
    AVAILABLE("AVAILABLE"),
    BOOKED("BOOKED"),
    UNDER_MAINTENANCE("UNDER_MAINTENANCE"),
    DELETED("DELETED"),
    CLEANING("CLEANING");

    public final String value;

    RoomStatus(String value) {
        this.value = value;
    }
}
