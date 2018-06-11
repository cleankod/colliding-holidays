package cleankod.bs.holiday.client.domain;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class GetHolidaysRequest {
    private final String country;
    private final String year;
    private final String month;
    private final String day;
}
