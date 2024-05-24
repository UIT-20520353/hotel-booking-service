package com.service.hotel_booking.enumerations;

public enum PropertyType {

    HOTEL("HOTEL"),
    VILLA("VILLA"),
    APARTMENT("APARTMENT"),
    MOTEL("MOTEL");

    public final String value;
    PropertyType(String value) {
        this.value = value;
    }

}
