package cleankod.bs.holiday;

import java.time.Clock;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;

import cleankod.bs.holiday.client.HolidayClient;
import cleankod.bs.holiday.client.HolidayClientFactory;
import cleankod.bs.holiday.client.domain.ApiKey;
import cleankod.bs.holiday.client.domain.BaseUrl;
import cleankod.bs.holiday.core.HolidayService;
import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HolidayClient holidayClient(Environment environment, ObjectMapper mapper) {
        ApiKey apiKey = new ApiKey(environment.getRequiredProperty("application.holiday-api-key"));
        BaseUrl baseUrl = new BaseUrl(environment.getRequiredProperty("application.holiday-api-base-url"));
        return HolidayClientFactory.getInstance(apiKey, baseUrl, mapper);
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

    @Bean
    boolean onlyPastSupported(Environment environment) {
        return environment.getRequiredProperty("application.only-past-supported", Boolean.class);
    }

    @Bean
    @Profile("!test")
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
