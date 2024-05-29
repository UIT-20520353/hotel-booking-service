package com.service.hotel_booking.enumerations;

public enum PropertyStatus {

    AVAILABLE("AVAILABLE"),
    REPAIRING("REPAIRING"),
    DELETED("DELETED");

    public final String value;
    PropertyStatus(String value) {
        this.value = value;
    }

}
