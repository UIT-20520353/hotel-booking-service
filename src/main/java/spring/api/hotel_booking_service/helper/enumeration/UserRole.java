package spring.api.hotel_booking_service.helper.enumeration;

import lombok.Getter;

@Getter
public enum UserRole {

    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    BUSINESS_OWNER("BUSINESS_OWNER"),
    CUSTOMER("CUSTOMER"),
    STAFF("STAFF");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

}