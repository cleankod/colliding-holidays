package cleankod.bs.holiday.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cleankod.bs.holiday.core.domain.GetCollidingHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CollidingHolidayService {

    private final HolidayPerCountryFetcher holidayPerCountryFetcher;

    List<Holiday> getHolidaysOnTheSameDayInAllCountries(GetCollidingHolidaysRequest getCollidingHolidaysRequest) {
        Map<String, List<Holiday>> holidays;
        var actualDate = getCollidingHolidaysRequest.getDate();
        do {
            holidays = holidayPerCountryFetcher.fetchHolidays(getCollidingHolidaysRequest, actualDate);
            actualDate = actualDate.plusDays(1L);
        }
        while (holidays.size() < getCollidingHolidaysRequest.getCountries().size());

        return holidays.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
