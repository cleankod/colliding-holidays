package cleankod.bs.holiday.client.domain

import spock.lang.Specification
import spock.lang.Unroll

class BaseUrlSpec extends Specification {
    @Unroll
    def "should not build the object due to invalid format for value #givenValue"() {
        when:
        new BaseUrl(givenValue)

        then:
        thrown(IllegalArgumentException)

        where:
        givenValue << [
                null,
                "invalid-value",
                "file://example.com/v1",
                "sftp://example.com/v1",
                "example.com/v1",
                "https://"
        ]
    }

    @Unroll
    def "should build the object for value #givenValue"() {
        when:
        def key = new BaseUrl(givenValue)

        then:
        key.value == givenValue
        noExceptionThrown()

        where:
        givenValue << [
                "http://example.com/v1",
                "http://localhost:8080/v1",
                "https://example.com/v1",
                "https://example.com/v1/",
                "https://subdomain.example.com/v1",

        ]
    }
}
