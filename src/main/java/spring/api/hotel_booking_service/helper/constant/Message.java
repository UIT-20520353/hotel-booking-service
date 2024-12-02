package spring.api.hotel_booking_service.helper.constant;

import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class Message {

    // Title
    public static String BAD_REQUEST_TITLE = "Bad Request";
    public static String UNAUTHORIZED_TITLE = "Unauthorized";
    public static String INTERNAL_SERVER_TITLE = "Internal Server Error";
    public static String NOT_FOUND_TITLE = "Not Found";

    // Message
    public static String BODY_MISSING_MSG = "Required request body is missing";
    public static String INTERNAL_SERVER_ERROR = "error.internal-server";
    public static String UNAUTHORIZED = "error.unauthorized";

    // Province
    public static String PROVINCE_NOT_FOUND = "error.province.not-found";

    // Authentication
    public static final String EMAIL_REQUIRED_ERROR = "error.validate.email.required";
    public static final String EMAIL_FORMAT_ERROR = "error.validate.email.format";
    public static final String PASSWORD_REQUIRED_ERROR = "error.validate.password.required";

}
