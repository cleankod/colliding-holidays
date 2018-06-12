package cleankod.bs.holiday.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

public class HolidayClientFactory {
    public static HolidayClient getInstance(ObjectMapper mapper) {
        JacksonDecoder decoder = new JacksonDecoder(mapper);
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(mapper))
                .decoder(decoder)
                .requestInterceptor(template ->
                        template.query("key", "99f1f2f3-51e7-4999-a88c-f0e64a91c56f")
                )
                .errorDecoder(
                        AnnotationErrorDecoder.builderFor(HolidayClient.class)
                                .withResponseBodyDecoder(decoder)
                                .build()
                )
                .target(HolidayClient.class, "https://holidayapi.com/v1");
    }
}
