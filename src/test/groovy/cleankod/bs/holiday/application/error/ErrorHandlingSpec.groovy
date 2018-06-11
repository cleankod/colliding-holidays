package cleankod.bs.holiday.application.error

import cleankod.bs.holiday.BaseMvcSpec
import cleankod.bs.holiday.application.error.Error

class ErrorHandlingSpec extends BaseMvcSpec {
    def "should handle exception"() {
        given:
        def message = "some message"

        when:
        def response = get("/throw-exception", [class: [givenExceptionClassName], message: [message]])

        then:
        response.status == actualStatus
        with(getResponseAs(response, Error)) {
            it.id.length() == 16
            it.message == message
        }

        where:
        givenExceptionClassName          || actualStatus
        IllegalStateException.class.name || 500
    }
}
