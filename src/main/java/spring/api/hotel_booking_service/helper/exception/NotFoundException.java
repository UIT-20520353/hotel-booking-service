package spring.api.hotel_booking_service.helper.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import static spring.api.hotel_booking_service.helper.constant.Message.NOT_FOUND_TITLE;

public class NotFoundException extends AbstractThrowableProblem {
    public NotFoundException(String message) {
        super(null, NOT_FOUND_TITLE, Status.NOT_FOUND, message);
    }
}
