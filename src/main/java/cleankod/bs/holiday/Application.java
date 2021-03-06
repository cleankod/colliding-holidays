package cleankod.bs.holiday;

import java.time.Clock;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;

import cleankod.bs.holiday.application.cache.CacheConfiguration;
import cleankod.bs.holiday.application.properties.ApplicationProperties;
import cleankod.bs.holiday.application.log.LoggingConfiguration;
import cleankod.bs.holiday.client.HolidayClient;
import cleankod.bs.holiday.client.HolidayClientFactory;
import cleankod.bs.holiday.client.domain.ApiKey;
import cleankod.bs.holiday.client.domain.BaseUrl;
import cleankod.bs.holiday.client.domain.Timeout;
import cleankod.bs.holiday.core.HolidayForSingleCountryFetcher;
import cleankod.bs.holiday.core.CollidingHolidayService;
import cleankod.bs.holiday.core.CollidingHolidayServiceFactory;
import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper;

@SpringBootApplication
@Import({ApplicationProperties.class, CacheConfiguration.class, LoggingConfiguration.class})
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    HolidayClient holidayClient(ApplicationProperties applicationProperties, ObjectMapper mapper) {
        ApiKey apiKey = new ApiKey(applicationProperties.getHolidayApi().getKey());
        BaseUrl baseUrl = new BaseUrl(applicationProperties.getHolidayApi().getBaseUrl());
        Timeout timeout = new Timeout(applicationProperties.getHolidayApi().getTimeoutDuration());
        return HolidayClientFactory.create(apiKey, baseUrl, timeout, mapper);
    }

    @Bean
    HolidayForSingleCountryFetcher holidayForSingleCountryFetcher(HolidayClient holidayClient) {
        return new HolidayForSingleCountryFetcher(holidayClient);
    }

    @Bean
    CollidingHolidayService holidayService(HolidayForSingleCountryFetcher holidayForSingleCountryFetcher) {
        return CollidingHolidayServiceFactory.create(holidayForSingleCountryFetcher);
    }

    @Bean
    SupportedCountriesWrapper supportedCountriesWrapper(ApplicationProperties applicationProperties) {
        return new SupportedCountriesWrapper(
                Arrays.asList(applicationProperties.getSupportedCountries())
        );
    }

    @Bean
    boolean onlyPastSupported(ApplicationProperties applicationProperties) {
        return applicationProperties.getOnlyPastSupported();
    }

    @Bean
    @Profile("!test")
    Clock clock() {
        return Clock.systemDefaultZone();
    }
}
