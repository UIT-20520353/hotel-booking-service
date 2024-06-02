package com.service.hotel_booking.enumerations;

public enum AmenityType {

    PROPERTY("PROPERTY"),
    ROOM("ROOM");

    public final String value;
    AmenityType(String value) {
        this.value = value;
    }

}
