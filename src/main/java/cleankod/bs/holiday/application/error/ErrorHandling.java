package cleankod.bs.holiday.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandling {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleUnknownException(Exception ex) {
        Error error = new Error(ex.getMessage());
        log.error("Unknown error. Message: {}, errorId: {}.", error.getMessage(), error.getId(), ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
