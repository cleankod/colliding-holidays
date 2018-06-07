package cleankod.bs.holiday.core;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cleankod.bs.holiday.core.domain.GetHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import cleankod.bs.holiday.client.HolidayClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HolidayService {
    private final HolidayClient holidayClient;

    List<Holiday> getHolidays(GetHolidaysRequest getHolidaysRequest) {
        var givenDate = LocalDate.parse(getHolidaysRequest.getDate());
        Map<String, List<Holiday>> holidays;
        var actualDate = givenDate;
        do {
            holidays = fetchHolidays(getHolidaysRequest, actualDate);
            actualDate = actualDate.plusDays(1L);
        }
        while (holidays.size() < getHolidaysRequest.getCountries().size());

        return holidays.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private Map<String, List<Holiday>> fetchHolidays(GetHolidaysRequest getHolidaysRequest, LocalDate actualDate) {
        return getHolidaysRequest.getCountries().stream()
                .map(country -> getHolidays(actualDate, country))
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Holiday::getCountry));
    }

    private List<Holiday> getHolidays(LocalDate date, String country) {
        var request = new cleankod.bs.holiday.client.domain.GetHolidaysRequest(
                country,
                String.valueOf(date.getYear()),
                String.valueOf(date.getMonth().getValue()),
                String.valueOf(date.getDayOfMonth())
        );
        return holidayClient.holidays(request)
                .getHolidays()
                .stream()
                .map(holiday -> convertHoliday(country, holiday))
                .collect(Collectors.toList());
    }

    private Holiday convertHoliday(String country, cleankod.bs.holiday.client.domain.Holiday holiday) {
        return new Holiday(holiday.getName(), country, holiday.getDate());
    }
}
