package cleankod.bs.holiday;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import cleankod.bs.holiday.application.converter.DateTimeConverterConfiguration;
import cleankod.bs.holiday.client.HolidayClient;
import cleankod.bs.holiday.core.HolidayService;
import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

@SpringBootApplication
@Import(DateTimeConverterConfiguration.class)
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HolidayClient holidayClient(ObjectMapper mapper) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(mapper))
                .decoder(new JacksonDecoder(mapper))
                .requestInterceptor(template ->
                        template.query("key", "99f1f2f3-51e7-4999-a88c-f0e64a91c56f")
                )
                .target(HolidayClient.class, "https://holidayapi.com/v1");
    }

    @Bean
    HolidayService holidayService(HolidayClient holidayClient) {
        return new HolidayService(holidayClient);
    }

    @Bean
    SupportedCountriesWrapper supportedCountriesWrapper(Environment environment) {
        return new SupportedCountriesWrapper(
                Arrays.asList(environment.getRequiredProperty("application.supported-countries", String[].class))
        );
    }
}
