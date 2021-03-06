package cleankod.bs.holiday.core

import cleankod.bs.holiday.BaseMvcSpec
import cleankod.bs.holiday.application.error.ErrorResponse
import cleankod.bs.holiday.application.error.FieldError
import spock.lang.Unroll

class HolidaySpec extends BaseMvcSpec {

    @Unroll
    def "should return a list of next colliding holidays for #countries, #date"() {
        when:
        def response = get("/colliding-holidays", [countries: countries, date: [date]])

        then:
        response.status == 200

        and:
        with(getResponseAs(response, CollidingHolidayController.HolidayWrapper), {
            it.holidays.collect { it.name } == names
        })

        where:
        countries          | date         || names
        ["PL", "NO"]       | "2016-03-27" || ["2. påskedag", "Poniedziałek Wielkanocny"]
        ["PL", "NO"]       | "2016-03-28" || ["2. påskedag", "Poniedziałek Wielkanocny"]
        ["PL", "NO"]       | "2016-01-01" || ["1. nyttårsdag", "Nowy Rok"]
        ["PL", "NO"]       | "2015-12-28" || ["1. nyttårsdag", "Nowy Rok"]
        ["PL", "NO"]       | "2015-12-25" || ["1. juledag", "Pierwszy dzień Bożego Narodzenia"]
        ["PL", "NO"]       | "2015-12-24" || ["1. juledag", "Pierwszy dzień Bożego Narodzenia"]
        ["PL", "NO", "US"] | "2015-12-20" || ["1. juledag", "Pierwszy dzień Bożego Narodzenia", "Christmas Day"]
    }

    def "should not return since unsupported countries given"() {
        given:
        def givenCountries = ["PL", "NO", "ES", "US"]

        when:
        def response = get("/colliding-holidays", [countries: givenCountries, date: ["2015-12-28"]])

        then:
        response.status == 422
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            it.fieldErrors.size == 1
            it.fieldErrors.contains(
                    new FieldError("countries", "{validation.unsupported-countries}", givenCountries)
            )
        }
    }

    @Unroll
    def "should not return since unsupported date #givenDate"() {
        when:
        def response = get("/colliding-holidays", [countries: ["PL"], date: [givenDate]])

        then:
        response.status == 422
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            it.fieldErrors.size == 1
            it.fieldErrors.contains(new FieldError("date", "{validation.unsupported-date}", givenDate))
        }

        where:
        givenDate << [
                "2019-01-01", "2018-07-01", "2018-06-11", "2018-06-10", "2018-06-01"
        ]
    }

    def "should not return since required parameters not given"() {
        when:
        def response = get("/colliding-holidays")

        then:
        response.status == 422
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            it.fieldErrors.containsAll(
                    new FieldError("date", "{validation.date-required}", null),
                    new FieldError("countries", "{validation.countries-required}", null)
            )
        }
    }
}
