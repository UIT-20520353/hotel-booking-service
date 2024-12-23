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
    public static String BAD_REQUEST = "error.bad-request";

    // Province
    public static String PROVINCE_NOT_FOUND = "error.province.not-found";

    // Authentication
    public static final String EMAIL_REQUIRED_ERROR = "error.validate.email.required";
    public static final String EMAIL_FORMAT_ERROR = "error.validate.email.format";
    public static final String PASSWORD_REQUIRED_ERROR = "error.validate.password.required";
    public static final String LOGIN_TYPE_REQUIRED_ERROR = "error.validate.login-type.required";

    public static final String IMAGE_REQUIRED_ERROR = "error.validate.image.required";
    public static final String IMAGE_UPLOAD_FAIL_ERROR = "error.validate.image.upload-fail";

    public static final String FIRST_NAME_REQUIRED_ERROR = "error.validate.first-name.required";
    public static final String LAST_NAME_REQUIRED_ERROR = "error.validate.last-name.required";
    public static final String DATE_OF_BIRTH_REQUIRED_ERROR = "error.validate.date-of-birth.required";
    public static final String GENDER_REQUIRED_ERROR = "error.validate.gender.required";

    public static final String USER_NOT_FOUND_ERROR = "error.user.not-found";
    public static final String INVALID_CREDENTIAL_ERR = "error.validate.login.invalid-credential";

    // Attraction
    public static final String ATTRACTION_NOT_FOUND = "error.attraction.not-found";
    public static final String ATTRACTION_NAME_REQUIRED_ERROR = "error.validate.attraction.name.required";
    public static final String ATTRACTION_NAME_MAX_LENGTH_ERROR = "error.validate.attraction.name.max-length";
    public static final String ATTRACTION_DESCRIPTION_REQUIRED_ERROR = "error.validate.attraction.description.required";
    public static final String ATTRACTION_ADDRESS_REQUIRED_ERROR = "error.validate.attraction.address.required";
    public static final String ATTRACTION_OVERVIEW_IMAGE_REQUIRED_ERROR = "error.validate.attraction.overview-image.required";
    public static final String ATTRACTION_SUMMARY_REQUIRED_ERROR = "error.validate.attraction.summary.required";
    public static final String ATTRACTION_LATITUDE_REQUIRED_ERROR = "error.validate.attraction.latitude.required";
    public static final String ATTRACTION_LONGITUDE_REQUIRED_ERROR = "error.validate.attraction.longitude.required";
    public static final String ATTRACTION_OVERVIEW_IMAGE_INVALID = "error.validate.attraction.overview-image.invalid";

    // File
    public static final String FILE_SIZE_ERROR = "error.validate.file.size";
    public static final String FILE_TYPE_ERROR = "error.validate.file.type";

}
