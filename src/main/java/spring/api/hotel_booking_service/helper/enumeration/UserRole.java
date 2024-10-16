package spring.api.hotel_booking_service.helper.enumeration;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    ARGENT("ARGENT");

    public final String value;
    UserRole(String value) {
        this.value = value;
    }

}