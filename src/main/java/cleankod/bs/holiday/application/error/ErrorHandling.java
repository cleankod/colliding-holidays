package cleankod.bs.holiday.application.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        GlobalError globalError = new GlobalError(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(globalError);
        log.error("Unknown error. Message: {}, errorId: {}.", globalError.getMessage(), errorResponse.getId(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
