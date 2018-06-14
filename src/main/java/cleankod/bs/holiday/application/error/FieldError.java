package cleankod.bs.holiday.application.error;

import lombok.Data;

@Data
public class FieldError {
    private final String field;
    private final String code;
    private final Object rejectedValue;
}
