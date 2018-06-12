package cleankod.bs.holiday.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import cleankod.bs.holiday.client.domain.ApiKey;
import cleankod.bs.holiday.client.domain.BaseUrl;
import feign.Feign;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

public class HolidayClientFactory {
    public static HolidayClient getInstance(ApiKey apiKey, BaseUrl baseUrl, ObjectMapper mapper) {
        JacksonDecoder decoder = new JacksonDecoder(mapper);
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(mapper))
                .decoder(decoder)
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
