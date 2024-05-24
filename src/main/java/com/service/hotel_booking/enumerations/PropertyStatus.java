package com.service.hotel_booking.enumerations;

public enum PropertyStatus {

    AVAILABLE("AVAILABLE"),
    RENT("RENT");

    public final String value;
    PropertyStatus(String value) {
        this.value = value;
    }

}
