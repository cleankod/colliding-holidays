package cleankod.bs.holiday.client.domain;

import java.util.List;

import lombok.Data;

@Data
public class HolidayWrapper {
    private final List<Holiday> holidays;
}
