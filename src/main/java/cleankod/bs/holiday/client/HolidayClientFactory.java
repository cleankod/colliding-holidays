package cleankod.bs.holiday.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.config.ConfigurationManager;

import cleankod.bs.holiday.client.domain.ApiKey;
import cleankod.bs.holiday.client.domain.BaseUrl;
import cleankod.bs.holiday.client.domain.Timeout;
import feign.Logger;
import feign.error.AnnotationErrorDecoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class HolidayClientFactory {

    private static final String HYSTRIX_TIMEOUT_CONFIGURATION_KEY =
            "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds";

    public static HolidayClient create(ApiKey apiKey, BaseUrl baseUrl, Timeout timeout, ObjectMapper mapper) {
        JacksonDecoder decoder = new JacksonDecoder(mapper);
        ConfigurationManager.getConfigInstance()
                .setProperty(HYSTRIX_TIMEOUT_CONFIGURATION_KEY, timeout.getValue());
        return HystrixFeign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(mapper))
                .decoder(decoder)
                .logger(new Slf4jLogger(HolidayClient.class))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(template ->
                        template.query("key", apiKey.getValue())
                )
                .errorDecoder(
                        AnnotationErrorDecoder.builderFor(HolidayClient.class)
                                .withResponseBodyDecoder(decoder)
                                .build()
                )
                .target(HolidayClient.class, baseUrl.getValue());
    }
}
