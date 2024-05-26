package com.service.hotel_booking.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import static com.service.hotel_booking.exceptions.ExceptionTranslator.UNSUPPORTED_MEDIA_TYPE_TITLE;

public class UnsupportedMediaTypeStatusException extends AbstractThrowableProblem {
    public UnsupportedMediaTypeStatusException(String message) {
        super(null, UNSUPPORTED_MEDIA_TYPE_TITLE, Status.UNSUPPORTED_MEDIA_TYPE, message);
    }
}

