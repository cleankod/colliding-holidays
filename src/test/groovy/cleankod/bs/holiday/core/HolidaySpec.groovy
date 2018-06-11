package cleankod.bs.holiday.core

import cleankod.bs.holiday.BaseMvcSpec
import cleankod.bs.holiday.application.error.ErrorResponse
import spock.lang.Unroll

class HolidaySpec extends BaseMvcSpec {

    @Unroll
    def "should return a list of next colliding holidays for #countries, #date"() {
        when:
        def response = get("/holidays", [countries: countries, date: [date]])

        then:
        response.status == 200

        and:
        with(getResponseAs(response, HolidayController.HolidayWrapper), {
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
        def response = get("/holidays", [countries: givenCountries, date: ["2015-12-28"]])

        then:
        response.status == 422
        with(getResponseAs(response, ErrorResponse)) {
            it.id.length() == 16
            def fieldError = it.fieldErrors.get(0)
            fieldError.field == "countries"
            fieldError.code == "{validation.unsupported-countries}"
            fieldError.rejectedValue == givenCountries
        }
    }
}
