package cleankod.bs.holiday

class HolidaySpec extends BaseMvcSpec {
    def "should return a list of holidays"() {
        when:
        def response = get("/holidays")

        then:
        response.status == 200

        and:
        with(getResponseAs(response, HolidayController.HolidayWrapper), {
            it.holidays.get(0).name == "Zesłanie Ducha Świętego"
            it.holidays.get(1).name == "Dzień Bożego Ciała"
        })
    }
}
