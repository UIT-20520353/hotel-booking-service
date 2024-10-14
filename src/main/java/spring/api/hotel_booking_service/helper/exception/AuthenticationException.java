package spring.api.hotel_booking_service.helper.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import static spring.api.hotel_booking_service.helper.constant.Message.UNAUTHORIZED_TITLE;

public class AuthenticationException extends AbstractThrowableProblem {

    public AuthenticationException(String message) {
        super(null, UNAUTHORIZED_TITLE, Status.UNAUTHORIZED, message);
    }

}
