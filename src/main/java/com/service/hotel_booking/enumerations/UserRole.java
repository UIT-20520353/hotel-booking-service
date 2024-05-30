package com.service.hotel_booking.enumerations;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    ARGENT("ARGENT");

    public final String value;
    UserRole(String value) {
        this.value = value;
    }

}
