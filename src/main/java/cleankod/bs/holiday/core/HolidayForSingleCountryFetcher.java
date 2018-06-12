package cleankod.bs.holiday.core;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.cache.annotation.CacheResult;

import cleankod.bs.holiday.client.HolidayClient;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HolidayForSingleCountryFetcher {
    private final HolidayClient holidayClient;

    @CacheResult(cacheName = "holidays")
    List<Holiday> getHolidays(LocalDate date, String country) {
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
