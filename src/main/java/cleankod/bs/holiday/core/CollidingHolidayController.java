package cleankod.bs.holiday.core;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleankod.bs.holiday.core.domain.GetCollidingHolidaysRequest;
import cleankod.bs.holiday.core.domain.Holiday;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/colliding-holidays")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class CollidingHolidayController {

    private final CollidingHolidayService collidingHolidayService;

    @GetMapping
    public HolidayWrapper get(@Valid GetCollidingHolidaysRequest getCollidingHolidaysRequest) {
        return new HolidayWrapper(collidingHolidayService.getHolidaysOnTheSameDayInAllCountries(getCollidingHolidaysRequest));
    }

    @Data
    static class HolidayWrapper {
        private final List<Holiday> holidays;
    }
}
