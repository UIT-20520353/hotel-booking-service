package com.service.hotel_booking.constant;

public class MessageConstant {

    public MessageConstant() {}

    public static final String UNSUPPORTED_MEDIA_TYPE = "error.validate.media-type.unsupported";

    public static final String INVALID_CREDENTIAL_ERR = "error.validate.login.invalid-credential";

    public static final String USER_ALREADY_EXIST_ERR = "error.validate.user.already-exist";
    public static final String USER_NOT_EXIST = "error.user.not-exist";

    public static final String EMAIL_REQUIRED_ERROR = "error.validate.email.required";
    public static final String EMAIL_FORMAT_ERROR = "error.validate.email.format";
    public static final String EMAIL_MIN_LENGTH_ERROR = "error.validate.email.length.min";
    public static final String EMAIL_MAX_LENGTH_ERROR = "error.validate.email.length.max";

    public static final String PASSWORD_REQUIRED_ERROR = "error.validate.password.required";
    public static final String PASSWORD_MIN_LENGTH_ERROR = "error.validate.password.length.min";
    public static final String PASSWORD_MAX_LENGTH_ERROR = "error.validate.password.length.max";

    public static final String FIRST_NAME_REQUIRED_ERROR = "error.validate.first-name.required";
    public static final String FIRST_NAME_MAX_LENGTH_ERROR = "error.validate.first-name.length.max";
    public static final String LAST_NAME_REQUIRED_ERROR = "error.validate.last-name.required";
    public static final String LAST_NAME_MAX_LENGTH_ERROR = "error.validate.last-name.length.max";

    public static final String PHONE_NUMBER_REQUIRED_ERROR = "error.validate.phone-number.required";
    public static final String PHONE_NUMBER_MAX_LENGTH_ERROR = "error.validate.phone-number.length.max";

    public static final String USER_IS_NOT_ADMIN = "error.validate.user.is-not-admin";
    public static final String USER_IS_NOT_ARGENT = "error.validate.user.is-not-argent";
    public static final String ARGENT_ALREADY_ACTIVE = "error.argent.already-active";
    public static final String ARGENT_ALREADY_REJECTED = "error.argent.already-rejected";
    public static final String ARGENT_ALREADY_BLOCKED = "error.argent.already-blocked";

    public static final String WARD_NOT_EXIST = "error.ward.not-exist";
    public static final String DISTRICT_NOT_EXIST = "error.district.not-exist";
    public static final String PROVINCE_NOT_EXIST = "error.province.not-exist";

    public static final String IMAGE_TYPE_INVALID_ERROR = "error.validate.file.image.invalid-file";
    public static final String IMAGE_SIZE_INVALID_ERROR = "error.validate.file.image.invalid-size";
    public static final String AUDIO_TYPE_INVALID_ERROR = "error.validate.file.audio.invalid-file";
    public static final String AUDIO_SIZE_INVALID_ERROR = "error.validate.file.audio.invalid-size";
    public static final String  VIDEO_TYPE_INVALID_ERROR = "error.validate.file.video.invalid-file";
    public static final String VIDEO_SIZE_INVALID_ERROR = "error.validate.file.video.invalid-size";

    public static final String IDENTITY_NUMBER_REQUIRED_ERROR = "error.validate.identity-number.required";
    public static final String IDENTITY_NUMBER_MAX_LENGTH_ERROR = "error.validate.identity-number.length.max";
    public static final String IDENTITY_NUMBER_MIN_LENGTH_ERROR = "error.validate.identity-number.length.min";

    public static final String FRONT_IDENTITY_CARD_REQUIRED_ERROR = "error.validate.front-identity-card.required";
    public static final String BACK_IDENTITY_CARD_REQUIRED_ERROR = "error.validate.back-identity-card.required";
    public static final String SELFIE_IMG_REQUIRED_ERROR = "error.validate.selfie-img.required";

    public static final String ACCOUNT_PENDING_APPROVAL = "error.validate.login.pending-approval";

    public static final String AMENITY_REQUIRED_ERROR = "error.validate.amenity.required";
    public static final String AMENITY_NAME_MAX_LENGTH_ERROR = "error.validate.amenity-name.length.max";
    public static final String AMENITY_TYPE_INVALID_ERROR = "error.validate.amenity-type.invalid";
    public static final String AMENITY_TYPE_REQUIRED_ERROR = "error.validate.amenity-type.required";
    public static final String AMENITY_NOT_EXIST = "error.amenity.not-exist";

    //      Property message        //
    public static final String PROPERTY_NOT_EXIST = "error.property.not-exist";
    public static final String PROPERTY_NAME_REQUIRED_ERROR = "error.validate.property-name.required";
    public static final String PROPERTY_NAME_MAX_LENGTH_ERROR = "error.validate.property-name.length.max";
    public static final String PROPERTY_DESCRIPTION_REQUIRED_ERROR = "error.validate.property-description.required";
    public static final String PROPERTY_ADDRESS_REQUIRED_ERROR = "error.validate.property-address.required";
    public static final String PROPERTY_ADDRESS_MAX_LENGTH_ERROR = "error.validate.property-address.length.max";
    public static final String PROPERTY_LONGITUDE_REQUIRED_ERROR = "error.validate.property-longitude.required";
    public static final String PROPERTY_LONGITUDE_MAX_VALUE_ERROR = "error.validate.property-longitude.value.max";
    public static final String PROPERTY_LONGITUDE_MIN_VALUE_ERROR = "error.validate.property-longitude.value.min";
    public static final String PROPERTY_LONGITUDE_DIGITS_ERROR = "error.validate.property-longitude.value.digits";
    public static final String PROPERTY_LATITUDE_REQUIRED_ERROR = "error.validate.property-latitude.required";
    public static final String PROPERTY_LATITUDE_MAX_VALUE_ERROR = "error.validate.property-latitude.value.max";
    public static final String PROPERTY_LATITUDE_MIN_VALUE_ERROR = "error.validate.property-latitude.value.min";
    public static final String PROPERTY_LATITUDE_DIGITS_ERROR = "error.validate.property-latitude.value.digits";
    public static final String PROPERTY_STATUS_INVALID_ERROR = "error.validate.property-status.value.invalid";
    public static final String PROPERTY_STATUS_REQUIRED_ERROR = "error.validate.property-status.required";
    public static final String PROPERTY_TYPE_INVALID_ERROR = "error.validate.property-type.value.invalid";
    public static final String PROPERTY_TYPE_REQUIRED_ERROR = "error.validate.property-type.required";
    public static final String PROPERTY_IMAGE_LIST_REQUIRED_ERROR = "error.validate.property-img-list.required";
    public static final String PROPERTY_WARD_ID_REQUIRED_ERROR = "error.validate.property-ward-id.required";
    public static final String PROPERTY_DISTRICT_ID_REQUIRED_ERROR = "error.validate.property-district-id.required";
    public static final String PROPERTY_PROVINCE_ID_REQUIRED_ERROR = "error.validate.property-province-id.required";
    public static final String PROPERTY_ARGENT_ID_REQUIRED_ERROR = "error.validate.property-argent-id.required";
    public static final String PROPERTY_PRICE_REQUIRED_ERROR = "error.validate.property-price.required";
    public static final String PROPERTY_PRICE_INTEGER_ERROR = "error.validate.property-price.integer";
    public static final String PROPERTY_AMENITY_ID_INTEGER_ERROR = "error.validate.property-amenity-id.integer";
    public static final String DISTRICT_NOT_IN_PROVINCE_ERROR = "error.validate.district-not-in-province";
    public static final String WARD_NOT_IN_DISTRICT_ERROR = "error.validate.ward-not-in-district";

}
