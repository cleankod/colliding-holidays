package cleankod.bs.holiday.core.domain;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetCollidingHolidaysRequest {
    @NotEmpty(message = "{validation.countries-required}")
    @SupportedCountries
    private final List<String> countries;
    @NotNull(message = "{validation.date-required}")
    @SupportedDate
    private final LocalDate date;
}
