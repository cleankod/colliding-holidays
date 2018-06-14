package cleankod.bs.holiday.application.error

import cleankod.bs.holiday.BaseMvcSpec

class ErrorHandlingSpec extends BaseMvcSpec {
    def "should handle exception"() {
        given:
        def message = "some message"

        when:
        def response = get("/throw-exception", [class: [givenExceptionClassName], message: [message]])

        then:
        response.status == actualStatus
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            it.globalErrors.get(0).message == message
        }

        where:
        givenExceptionClassName          || actualStatus
        IllegalStateException.class.name || 500
    }

    def "should handle client exception"() {
        given:
        def message = "some message"

        when:
        def response = get("/throw-client-exception", [message: [message], status: [status as String]])

        then:
        response.status == status
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            it.globalErrors.get(0).message == message
        }

        where:
        status << [401, 402, 403, 429, 500]
    }
}
