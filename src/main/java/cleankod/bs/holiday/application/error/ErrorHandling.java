package cleankod.bs.holiday.application.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cleankod.bs.holiday.client.exception.HolidayClientException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorHandling extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        ErrorResponse errorResponse = BindExceptionToErrorResponseConverter.getErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HolidayClientException.class)
    public final ResponseEntity<ErrorResponse> handleHolidayClientException(HolidayClientException ex) {
        ErrorResponse errorResponse = getSingleGlobalErrorResponse(ex.getMessage());
        log.warn("Holiday client error. Message: {}, errorId: {}.", ex.getMessage(), errorResponse.getId());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        ErrorResponse errorResponse = getSingleGlobalErrorResponse(ex.getMessage());
        log.error("Unknown error. Message: {}, errorId: {}.", ex.getMessage(), errorResponse.getId(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse getSingleGlobalErrorResponse(String message) {
        GlobalError globalError = new GlobalError(message);
        return new ErrorResponse(globalError);
    }
}
