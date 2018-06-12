package cleankod.bs.holiday.client.domain

import spock.lang.Specification
import spock.lang.Unroll

class ApiKeySpec extends Specification {
    @Unroll
    def "should not build the object due to invalid format for value #givenValue"() {
        when:
        new ApiKey(givenValue)

        then:
        thrown(IllegalArgumentException)

        where:
        givenValue << [
                null,
                "invalid-value",
                "b3a4564e-6e41-11e8-a600-0373507c7f0z",
                "b3a4564e-6e41-11e8-a600-0373507c7f012",
                "b3a4564e6e4111e8a6000373507c7f0a",
                "393B1BEE-6E42-11E8-A01D-0B2DD8B582A4"
        ]
    }

    @Unroll
    def "should build the object for value #givenValue"() {
        when:
        def key = new ApiKey(givenValue)

        then:
        key.value == givenValue
        noExceptionThrown()

        where:
        givenValue << [
                "b3a4564e-6e41-11e8-a600-0373507c7f0d",
                "99f1f2f3-51e7-4999-a88c-f0e64a91c56f",
                "393b1bee-6e42-11e8-a01d-0b2dd8b582a4"
        ]
    }
}
