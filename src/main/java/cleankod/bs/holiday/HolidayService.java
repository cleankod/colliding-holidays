package cleankod.bs.holiday;

import java.util.List;
import java.util.stream.Collectors;

import cleankod.bs.holiday.domain.Holiday;
import cleankod.bs.holiday.gateway.HolidayClient;
import cleankod.bs.holiday.gateway.domain.GetHolidaysRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HolidayService {
    private final HolidayClient holidayClient;

    public List<Holiday> getHolidays() {
        return holidayClient.holidays(new GetHolidaysRequest("PL", "2017", "06"))
                .getHolidays()
                .stream()
                .map(holiday -> new Holiday(holiday.getName()))
                .collect(Collectors.toList());
    }
}
