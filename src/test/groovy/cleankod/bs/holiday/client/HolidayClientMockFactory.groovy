package cleankod.bs.holiday.client

import net.jadler.Jadler
import net.jadler.Request
import net.jadler.stubbing.StubResponse
import net.jadler.stubbing.server.jdk.JdkStubHttpServer

import java.nio.charset.Charset

class HolidayClientMockFactory {

    static Jadler create() {
        Jadler.initJadlerUsing(new JdkStubHttpServer(9090))

        def holidaysResponder = { Request request ->
            def mockResponsesPath = "/holiday-api-responses${request.getURI().path}"
            def parameters = request.getParameters()
            def year = parameters.getValue("year")
            def month = parameters.getValue("month").padLeft(2, '0')
            def day = parameters.getValue("day").padLeft(2, '0')
            def country = parameters.getValue("country")
            def filename = "${mockResponsesPath}/${year}-${month}-${day}_${country}.json"
            def requestedResource = getClass().getResource(filename)
            def actualResource = Optional.ofNullable(requestedResource)
                    .orElseGet({ getClass().getResource("${mockResponsesPath}/empty.json") })
            return StubResponse.builder()
                    .body(actualResource.text, Charset.forName("UTF-8"))
                    .build()
        }
        return Jadler.onRequest()
                .havingMethodEqualTo("GET")
                .respondUsing(holidaysResponder)

    }
}
