package spring.api.hotel_booking_service.helper.enumeration;

import lombok.Getter;

@Getter
public enum UserGender {

    MALE("MALE"),
    FEMALE("FEMALE");

    private final String value;

    UserGender(String value) {
        this.value = value;
    }

}
