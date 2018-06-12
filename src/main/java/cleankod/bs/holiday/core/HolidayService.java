package cleankod.bs.holiday.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cleankod.bs.holiday.core.domain.GetHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HolidayService {

    private final HolidayPerCountryFetcher holidayPerCountryFetcher;

    List<Holiday> getHolidays(GetHolidaysRequest getHolidaysRequest) {
        Map<String, List<Holiday>> holidays;
        var actualDate = getHolidaysRequest.getDate();
        do {
            holidays = holidayPerCountryFetcher.fetchHolidays(getHolidaysRequest, actualDate);
            actualDate = actualDate.plusDays(1L);
        }
        while (holidays.size() < getHolidaysRequest.getCountries().size());

        return holidays.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
