package spring.api.hotel_booking_service.helper.constant;

import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class Message {

    // Title
    public static String BAD_REQUEST_TITLE = "Bad Request";
    public static String UNAUTHORIZED_TITLE = "Unauthorized";

    // Message
    public static String BODY_MISSING_MSG = "Required request body is missing";
    public static String INTERNAL_SERVER_ERROR = "error.internal-server";

}
