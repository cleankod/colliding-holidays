package cleankod.bs.holiday

import spock.lang.Unroll

class HolidaySpec extends BaseMvcSpec {

    @Unroll
    def "should return a list of holidays"() {
        when:
        def response = get("/holidays", [country: country, year: year, month: month])

        then:
        response.status == 200

        and:
        with(getResponseAs(response, HolidayController.HolidayWrapper), {
            it.holidays.collect { it.name } == names
        })

        where:
        country | year   | month || names
        "PL"    | "2017" | "06"  || ["Zesłanie Ducha Świętego", "Dzień Bożego Ciała"]
        "NO"    | "2016" | "03"  || ["Palmesøndag", "Skjærtorsdag", "Langfredag", "2. påskedag"]
    }
}
