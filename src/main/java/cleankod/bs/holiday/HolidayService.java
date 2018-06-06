package cleankod.bs.holiday;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import cleankod.bs.holiday.domain.GetHolidaysRequest;
import cleankod.bs.holiday.domain.Holiday;
import cleankod.bs.holiday.gateway.HolidayClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HolidayService {
    private final HolidayClient holidayClient;

    List<Holiday> getHolidays(GetHolidaysRequest getHolidaysRequest) {
        return getHolidaysRequest.getCountries().stream()
                .map(country -> getHolidays(getHolidaysRequest, country))
                .collect(Collectors.flatMapping(Collection::stream, Collectors.toList()));
    }

    private List<Holiday> getHolidays(GetHolidaysRequest getHolidaysRequest, String country) {
        var request = new cleankod.bs.holiday.gateway.domain.GetHolidaysRequest(
                country, getHolidaysRequest.getYear(), getHolidaysRequest.getMonth()
        );
        return holidayClient.holidays(request)
                .getHolidays()
                .stream()
                .map(holiday -> convertHoliday(country, holiday))
                .collect(Collectors.toList());
    }

    private Holiday convertHoliday(String country, cleankod.bs.holiday.gateway.domain.Holiday holiday) {
        return new Holiday(holiday.getName(), country, holiday.getDate());
    }
}
