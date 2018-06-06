package cleankod.bs.holiday

import spock.lang.Unroll

class HolidaySpec extends BaseMvcSpec {

    @Unroll
    def "should return a list of holidays for #countries, #date"() {
        when:
        def response = get("/holidays", [countries: countries, date: [date]])

        then:
        response.status == 200

        and:
        with(getResponseAs(response, HolidayController.HolidayWrapper), {
            it.holidays.collect { it.name } == names
        })

        where:
        countries    | date         || names
        ["PL"]       | "2017-06-01" || ["Zesłanie Ducha Świętego", "Dzień Bożego Ciała"]
        ["NO"]       | "2016-03-01" || ["Palmesøndag", "Skjærtorsdag", "Langfredag", "2. påskedag"]
        ["PL", "NO"] | "2016-03-01" || ["Niedziela Wielkanocna", "Poniedziałek Wielkanocny", "Palmesøndag", "Skjærtorsdag", "Langfredag", "2. påskedag"]
    }
}
