package cleankod.bs.holiday.core

import cleankod.bs.holiday.BaseMvcSpec
import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper

class CountrySpec extends BaseMvcSpec {

    def "should return supported countries list"() {
        when:
        def response = get('/countries')

        then:
        response.status == 200

        and:
        with(getResponseAs(response, SupportedCountriesWrapper)) {
            it.countries == ['PL', 'NO', 'US']
        }
    }
}