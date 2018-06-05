package cleankod.bs.holiday.gateway;

import cleankod.bs.holiday.gateway.domain.GetHolidaysRequest;
import cleankod.bs.holiday.gateway.domain.HolidayWrapper;
import feign.QueryMap;
import feign.RequestLine;

public interface HolidayClient {
    @RequestLine("GET /holidays")
    HolidayWrapper holidays(@QueryMap GetHolidaysRequest getHolidaysRequest);
}
