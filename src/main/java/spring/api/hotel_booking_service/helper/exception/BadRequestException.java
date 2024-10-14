package spring.api.hotel_booking_service.helper.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import static spring.api.hotel_booking_service.helper.constant.Message.BAD_REQUEST_TITLE;

public class BadRequestException extends AbstractThrowableProblem {
    public BadRequestException(String message) {
        super(null, BAD_REQUEST_TITLE, Status.BAD_REQUEST, message);
    }
}
