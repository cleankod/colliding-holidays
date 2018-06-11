package cleankod.bs.holiday.application.error;

import lombok.Data;

@Data
public class Error {
    private final String id = ErrorIdGenerator.generate();
    private final String message;
}
