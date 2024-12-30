package spring.api.hotel_booking_service.helper.enumeration;

import lombok.Getter;

@Getter
public enum BusinessOwnerStatus {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    BusinessOwnerStatus(String value) {
        this.value = value;
    }

}
