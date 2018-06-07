package cleankod.bs.holiday.core

import cleankod.bs.holiday.BaseMvcSpec
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
}
