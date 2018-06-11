package cleankod.bs.holiday.application.error;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String id = ErrorIdGenerator.generate();
    private final List<FieldError> fieldErrors;
    private final List<GlobalError> globalErrors;

    ErrorResponse(GlobalError globalErrors) {
        this.fieldErrors = null;
        this.globalErrors = Collections.singletonList(globalErrors);
    }

    ErrorResponse(List<FieldError> fieldErrors, List<GlobalError> globalErrors) {
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }
}
