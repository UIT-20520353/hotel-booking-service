package com.service.hotel_booking.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import static com.service.hotel_booking.exceptions.ExceptionTranslator.BAD_REQUEST_TITLE;

public class BadRequestException extends AbstractThrowableProblem {
    public BadRequestException(String message) {
        super(null, BAD_REQUEST_TITLE, Status.BAD_REQUEST, message);
    }
}
