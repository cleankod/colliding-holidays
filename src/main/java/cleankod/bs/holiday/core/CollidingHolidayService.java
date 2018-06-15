package cleankod.bs.holiday.core;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cleankod.bs.holiday.core.domain.GetCollidingHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CollidingHolidayService {

    private final HolidayPerCountryFetcher holidayPerCountryFetcher;

    List<Holiday> getHolidaysOnTheSameDayInAllCountries(GetCollidingHolidaysRequest getCollidingHolidaysRequest) {
        return Stream.iterate(getCollidingHolidaysRequest.getDate(), date -> date.plusDays(1L))
                .map(date -> holidayPerCountryFetcher.fetchHolidays(getCollidingHolidaysRequest, date))
                .filter(holidays -> holidays.size() >= getCollidingHolidaysRequest.getCountries().size())
                .findFirst()
                .map(this::getFlatHolidaysList)
                .orElse(Collections.emptyList());
    }

    private List<Holiday> getFlatHolidaysList(Map<String, List<Holiday>> holidays) {
        return holidays.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
