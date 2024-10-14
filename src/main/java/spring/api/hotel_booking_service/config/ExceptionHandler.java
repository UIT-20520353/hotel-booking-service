package spring.api.hotel_booking_service.config;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.violations.Violation;
import spring.api.hotel_booking_service.helper.constant.Message;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static spring.api.hotel_booking_service.helper.constant.Message.BAD_REQUEST_TITLE;
import static spring.api.hotel_booking_service.helper.constant.Message.BODY_MISSING_MSG;

@ControllerAdvice
public class ExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        Problem problem = entity.getBody();

        if (Objects.nonNull(problem) && Status.INTERNAL_SERVER_ERROR == problem.getStatus()) {
            Problem newProblem = Problem
                    .builder()
                    .withStatus(problem.getStatus())
                    .withTitle(problem.getTitle())
                    .withDetail(Message.INTERNAL_SERVER_ERROR)
                    .build();
            return new ResponseEntity<>(newProblem, entity.getHeaders(), entity.getStatusCode());
        }

        return ProblemHandling.super.process(entity, request);
    }

    @Override
    public ResponseEntity<Problem> newConstraintViolationProblem(Throwable throwable, Collection<Violation> stream, NativeWebRequest request) {
        List<Violation> violations = stream.stream()
                                           .sorted(Comparator.comparing(Violation::getField).thenComparing(Violation::getMessage))
                                           .toList();

        Problem problem = Problem
                .builder()
                .withStatus(Status.BAD_REQUEST)
                .withTitle(BAD_REQUEST_TITLE)
                .withDetail("Invalid request body")
                .with("violations", violations)
                .build();

        return this.create(throwable, problem, request);
    }

    @Override
    public ResponseEntity<Problem> handleMessageNotReadableException(HttpMessageNotReadableException exception, NativeWebRequest request) {
        String detail = exception.getMessage().startsWith(BODY_MISSING_MSG) ? BODY_MISSING_MSG : exception.getMessage();
        Problem problem = Problem
                .builder()
                .withStatus(Status.BAD_REQUEST)
                .withTitle(BAD_REQUEST_TITLE)
                .withDetail(detail)
                .build();
        return new ResponseEntity<>(problem, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<?> handleClientAbortException(ClientAbortException ex) {
        return ResponseEntity.noContent().build();
    }

}
