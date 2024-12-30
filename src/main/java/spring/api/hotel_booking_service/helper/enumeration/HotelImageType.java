package spring.api.hotel_booking_service.helper.enumeration;

import lombok.Getter;

@Getter
public enum HotelImageType {

    OVERVIEW("OVERVIEW"),
    DETAIL("DETAIL");

    private final String value;

    HotelImageType(String value) {
        this.value = value;
    }

}
