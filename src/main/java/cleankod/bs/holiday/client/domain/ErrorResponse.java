package cleankod.bs.holiday.client.domain;

import lombok.Data;

@Data
public class ErrorResponse {
    private final int status;
    private final String error;
}
