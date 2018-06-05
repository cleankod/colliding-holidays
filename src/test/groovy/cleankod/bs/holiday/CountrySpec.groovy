package cleankod.bs.holiday

class CountrySpec extends BaseMvcSpec {

    def "should return supported countries list"() {
        when:
        def response = get('/countries')

        then:
        response.status == 200

        and:
        with(getResponseAs(response, CountryController.CountryListWrapper)) {
            it.countries.get(0) == 'PL'
            it.countries.get(1) == 'NO'
        }
    }
}