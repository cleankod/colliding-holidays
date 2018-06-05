package cleankod.bs.holiday;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleankod.bs.holiday.domain.Holiday;
import cleankod.bs.holiday.gateway.HolidayClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class HolidayController {

    private final HolidayClient holidayClient;

    @GetMapping
    public HolidayWrapper get() {
        return getHolidays();
    }

    private HolidayWrapper getHolidays() {
        return new HolidayWrapper(
                holidayClient.holidays("PL", "2017", "06")
                        .getHolidays()
                        .stream()
                        .map(holiday -> new Holiday(holiday.getName()))
                        .collect(Collectors.toList())
        );
    }

    @Data
    static class HolidayWrapper {
        private final List<Holiday> holidays;
    }
}
