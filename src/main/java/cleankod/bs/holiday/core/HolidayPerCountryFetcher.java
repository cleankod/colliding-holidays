package cleankod.bs.holiday.core;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cleankod.bs.holiday.core.domain.GetCollidingHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HolidayPerCountryFetcher {
    private final HolidayForSingleCountryFetcher holidayForSingleCountryFetcher;

    Map<String, List<Holiday>> fetchHolidays(GetCollidingHolidaysRequest getCollidingHolidaysRequest, LocalDate actualDate) {
        return getCollidingHolidaysRequest.getCountries().stream()
                .map(country -> holidayForSingleCountryFetcher.getHolidays(actualDate, country))
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Holiday::getCountry));
    }


}
