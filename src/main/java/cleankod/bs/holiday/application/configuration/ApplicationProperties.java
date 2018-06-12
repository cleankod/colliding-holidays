package cleankod.bs.holiday.application.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "application")
@Configuration
@Getter
@Setter
public class ApplicationProperties {
    private String[] supportedCountries;
    private boolean onlyPastSupported;
    private HolidayApi holidayApi;

    @SuppressWarnings("WeakerAccess")
    @Getter
    @Setter
    public static class HolidayApi {
        private String key;
        private String baseUrl;
    }
}
