package cleankod.bs.holiday.core.domain

import spock.lang.Specification
import spock.lang.Unroll

import static java.lang.Boolean.FALSE
import static java.lang.Boolean.TRUE

class SupportedCountriesValidatorSpec extends Specification {
    @Unroll
    def "should be #expectedResult for #givenCountries"() {
        given:
        def validator = new SupportedCountriesValidator(new SupportedCountriesWrapper(["PL", "NO"]))

        expect:
        validator.isValid(givenCountries, null) == expectedResult

        where:
        givenCountries     || expectedResult
        null               || TRUE
        []                 || TRUE
        ["PL", "NO"]       || TRUE
        ["NO", "PL"]       || TRUE
        ["PL", "NO", "US"] || FALSE
        ["US", "PL", "NO"] || FALSE
        ["US"]             || FALSE
        ["US", "PL"]       || FALSE
        ["US", "NO"]       || FALSE
    }
}
