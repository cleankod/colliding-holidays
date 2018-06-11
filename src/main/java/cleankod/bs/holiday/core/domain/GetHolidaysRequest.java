package cleankod.bs.holiday.core.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetHolidaysRequest {
    @SupportedCountries
    private final List<String> countries;
    @SupportedDate
    private final LocalDate date;
}
