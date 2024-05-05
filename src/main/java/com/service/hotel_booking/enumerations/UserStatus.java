package com.service.hotel_booking.enumerations;

public enum UserStatus {

    CREATED("CREATED"),
    BLOCKED("BLOCKED"),
    ACTIVE("ACTIVE"),
    REJECTED("REJECTED");

    public final String value;
    UserStatus(String value) {
        this.value = value;
    }

}
