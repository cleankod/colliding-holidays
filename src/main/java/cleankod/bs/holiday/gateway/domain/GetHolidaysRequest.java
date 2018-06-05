package cleankod.bs.holiday.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetHolidaysRequest {
    private final String country;
    private final String year;
    private final String month;
}
