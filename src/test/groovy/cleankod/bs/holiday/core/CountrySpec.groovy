package cleankod.bs.holiday.core

import cleankod.bs.holiday.BaseMvcSpec

class CountrySpec extends BaseMvcSpec {

    def "should return supported countries list"() {
        when:
        def response = get('/countries')

        then:
        response.status == 200

        and:
        with(getResponseAs(response, CountryController.CountryListWrapper)) {
            it.countries == ['PL', 'NO']
        }
    }
}