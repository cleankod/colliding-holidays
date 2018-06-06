package cleankod.bs.holiday.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetHolidaysRequest {
    private final String country;
    private final String year;
    private final String month;
}
