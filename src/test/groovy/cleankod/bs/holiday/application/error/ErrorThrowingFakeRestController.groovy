package cleankod.bs.holiday.application.error

import cleankod.bs.holiday.client.exception.HolidayClientException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SuppressWarnings("GroovyUnusedDeclaration")
@RestController
class ErrorThrowingFakeRestController {
    @GetMapping(path = "/throw-exception")
    String throwException(
            @RequestParam("class") String exceptionClass,
            @RequestParam("message") String message
    ) throws Exception {
        throw (Exception) Class.forName(exceptionClass).getConstructor(String.class).newInstance(message)
    }

    @GetMapping(path = "/throw-client-exception")
    String throwException(
            @RequestParam("status") Integer status,
            @RequestParam("message") String message
    ) throws Exception {
        throw new HolidayClientException(new cleankod.bs.holiday.client.domain.ErrorResponse(status, message))
    }
}
