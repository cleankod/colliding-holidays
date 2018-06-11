package cleankod.bs.holiday.client.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HolidayWrapper {
    private final Integer status;
    private final List<Holiday> holidays;
}