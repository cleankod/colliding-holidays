package cleankod.bs.holiday.application.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

class BindExceptionToErrorResponseConverter {
    static ErrorResponse getErrorResponse(BindException ex) {
        BindingResult bindingResult = ex
                .getBindingResult();

        List<FieldError> fieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getRejectedValue())
                )
                .collect(Collectors.toList());

        List<GlobalError> globalErrors = bindingResult
                .getGlobalErrors()
                .stream()
                .map(globalError -> new GlobalError(
                        globalError.getCode())
                )
                .collect(Collectors.toList());

        return new ErrorResponse(fieldErrors, globalErrors);
    }
}