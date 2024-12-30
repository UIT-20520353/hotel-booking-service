package spring.api.hotel_booking_service.helper.enumeration;

import lombok.Getter;

@Getter
public enum HotelStatus {

    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String value;

    HotelStatus(String value) {
        this.value = value;
    }

}
