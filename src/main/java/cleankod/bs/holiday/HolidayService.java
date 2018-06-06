package cleankod.bs.holiday;

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
        return holidayClient.holidays(buildGetHolidaysRequest(getHolidaysRequest))
                .getHolidays()
                .stream()
                .map(holiday -> new Holiday(holiday.getName()))
                .collect(Collectors.toList());
    }

    private cleankod.bs.holiday.gateway.domain.GetHolidaysRequest buildGetHolidaysRequest(
            GetHolidaysRequest getHolidaysRequest
    ) {
        return new cleankod.bs.holiday.gateway.domain.GetHolidaysRequest(
                getHolidaysRequest.getCountry(),
                getHolidaysRequest.getYear(),
                getHolidaysRequest.getMonth()
        );
    }
}
