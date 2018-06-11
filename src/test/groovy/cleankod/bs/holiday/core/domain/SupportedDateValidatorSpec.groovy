package cleankod.bs.holiday.core.domain

import spock.lang.Specification
import spock.lang.Unroll

import java.time.Clock
import java.time.LocalDate

import static cleankod.bs.holiday.BaseMvcSpec.TestContextConfiguration.FIXED_NOW
import static cleankod.bs.holiday.BaseMvcSpec.TestContextConfiguration.FIXED_ZONE
import static java.lang.Boolean.FALSE
import static java.lang.Boolean.TRUE

class SupportedDateValidatorSpec extends Specification {
    @Unroll
    def "should be #isValid for #givenDate and when only past is supported #onlyPastSupported"() {
        given:
        def validator = new SupportedDateValidator(onlyPastSupported, Clock.fixed(FIXED_NOW, FIXED_ZONE))

        expect:
        validator.isValid(LocalDate.parse(givenDate), null) == isValid

        where:
        givenDate    | onlyPastSupported || isValid
        "2018-06-11" | TRUE              || FALSE
        "2018-06-11" | FALSE             || TRUE
        "2018-06-12" | FALSE             || TRUE
        "2018-07-12" | FALSE             || TRUE
        "2019-07-12" | FALSE             || TRUE
        "2019-07-12" | TRUE              || FALSE
        "2018-05-31" | TRUE              || TRUE
    }

    def "should be valid when null"() {
        given:
        def validator = new SupportedDateValidator(true, Clock.fixed(FIXED_NOW, FIXED_ZONE))

        when:
        def isValid = validator.isValid(null, null)

        then:
        isValid == TRUE
    }
}
