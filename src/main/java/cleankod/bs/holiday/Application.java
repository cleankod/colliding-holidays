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
import cleankod.bs.holiday.core.HolidayService;
import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HolidayClient holidayClient(ObjectMapper mapper) {
        return HolidayClientFactory.getInstance(mapper);
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
