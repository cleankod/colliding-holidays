package cleankod.bs.holiday.client.exception;

import java.util.Optional;

import cleankod.bs.holiday.client.domain.ErrorResponse;
import feign.error.FeignExceptionConstructor;
import feign.error.ResponseBody;
import lombok.Getter;

@Getter
public class HolidayClientException extends RuntimeException {
    private final int status;
    private final String message;

    @FeignExceptionConstructor
    public HolidayClientException(@ResponseBody ErrorResponse errorResponse) {
        this.status = Optional.ofNullable(errorResponse)
                .map(ErrorResponse::getStatus)
                .orElse(500);
        this.message = Optional.ofNullable(errorResponse)
                .map(ErrorResponse::getError)
                .orElse("Unknown client error.");
    }
}
