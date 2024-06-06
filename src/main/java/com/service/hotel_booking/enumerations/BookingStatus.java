package com.service.hotel_booking.enumerations;

public enum BookingStatus {

    PENDING("PENDING"),
    CHECKED_IN("CHECKED_IN"),
    CHECKED_OUT("CHECKED_OUT"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED"),
    CONFIRMED("CONFIRMED");

    public final String value;
    BookingStatus(String value) {
        this.value = value;
    }

}
