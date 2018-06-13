package cleankod.bs.holiday

import cleankod.bs.holiday.client.HolidayClientMockFactory
import com.fasterxml.jackson.databind.ObjectMapper
import net.jadler.Jadler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@WebMvcTest
@ActiveProfiles("test")
class BaseMvcSpec extends Specification {
    @Autowired
    protected MockMvc mvc

    @Autowired
    protected ObjectMapper objectMapper

    protected MockHttpServletResponse get(String uri) {
        mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn().response
    }

    protected MockHttpServletResponse get(String uri, Map<String, List<String>> params) {
        def entries = params.findAll { key, value -> value != null && !value.empty }
        MultiValueMap paramsMultiValueMap = new LinkedMultiValueMap(entries)
        mvc.perform(MockMvcRequestBuilders.get(uri).params(paramsMultiValueMap)).andReturn().response
    }

    protected <T> T getResponseAs(MockHttpServletResponse response, Class<T> clazz) {
        objectMapper.readValue(response.contentAsString, clazz)
    }

    static class TestContextConfiguration {
        public static final Instant FIXED_NOW = Instant.parse("2018-06-11T12:48:55Z")
        public static final ZoneId FIXED_ZONE = ZoneId.of("UTC")

        @Bean
        Jadler holidayApiServerMock() {
            return HolidayClientMockFactory.getInstance()
        }

        @Bean
        @Profile("test")
        Clock clock() {
            return Clock.fixed(FIXED_NOW, FIXED_ZONE)
        }
    }
}
