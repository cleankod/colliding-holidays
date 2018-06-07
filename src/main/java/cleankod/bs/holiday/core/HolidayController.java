package cleankod.bs.holiday.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleankod.bs.holiday.core.domain.GetHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class HolidayController {

    private final HolidayService holidayService;

    @GetMapping
    public HolidayWrapper get(GetHolidaysRequest getHolidaysRequest) {
        return new HolidayWrapper(holidayService.getHolidays(getHolidaysRequest));
    }

    @Data
    static class HolidayWrapper {
        private final List<Holiday> holidays;
    }
}
