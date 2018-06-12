package cleankod.bs.holiday.application.configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "application")
@Configuration
@Getter
@Setter
@Validated
public class ApplicationProperties {
    @NotNull private String[] supportedCountries;
    @NotNull private Boolean onlyPastSupported;
    @NotNull private HolidayApi holidayApi;

    @SuppressWarnings("WeakerAccess")
    @Getter
    @Setter
    public static class HolidayApi {
        @NotBlank private String key;
        @NotBlank private String baseUrl;
    }
}
