package cleankod.bs.holiday.client;

import cleankod.bs.holiday.client.domain.GetHolidaysRequest;
import cleankod.bs.holiday.client.domain.HolidayWrapper;
import cleankod.bs.holiday.client.exception.HolidayClientException;
import feign.QueryMap;
import feign.RequestLine;
import feign.error.ErrorHandling;

public interface HolidayClient {
    @ErrorHandling(defaultException = HolidayClientException.class)
    @RequestLine("GET /holidays")
    HolidayWrapper holidays(@QueryMap GetHolidaysRequest getHolidaysRequest) throws HolidayClientException;
}
