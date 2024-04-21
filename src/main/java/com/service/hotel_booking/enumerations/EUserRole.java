package com.service.hotel_booking.enumerations;

public enum EUserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    AGENT("AGENT");

    public final String value;
    EUserRole (String value) {
        this.value = value;
    }

}
