package cleankod.bs.holiday

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@WebMvcTest
class BaseMvcSpec extends Specification {
    @Autowired
    protected MockMvc mvc

    @Autowired
    protected ObjectMapper objectMapper

    protected MockHttpServletResponse get(String uri) {
        mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn().response
    }

    protected <T> T getResponseAs(MockHttpServletResponse response, Class<T> clazz) {
        objectMapper.readValue(response.contentAsString, clazz)
    }
}
