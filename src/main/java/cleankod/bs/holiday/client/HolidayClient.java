package cleankod.bs.holiday.client;

import cleankod.bs.holiday.client.domain.GetHolidaysRequest;
import cleankod.bs.holiday.client.domain.HolidayWrapper;
import feign.QueryMap;
import feign.RequestLine;

public interface HolidayClient {
    @RequestLine("GET /holidays")
    HolidayWrapper holidays(@QueryMap GetHolidaysRequest getHolidaysRequest);
}
