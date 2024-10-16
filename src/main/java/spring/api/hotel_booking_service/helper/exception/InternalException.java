package spring.api.hotel_booking_service.helper.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InternalException extends AbstractThrowableProblem {
    public InternalException() {
        super(null, "Internal Server Error", Status.INTERNAL_SERVER_ERROR, null);
    }

}

