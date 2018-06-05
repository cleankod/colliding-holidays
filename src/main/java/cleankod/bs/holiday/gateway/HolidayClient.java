package cleankod.bs.holiday.gateway;

import cleankod.bs.holiday.gateway.domain.HolidayWrapper;
import feign.Param;
import feign.RequestLine;

public interface HolidayClient {
    @RequestLine("GET /holidays?key={key}&country={country}&year={year}&month={month}")
    HolidayWrapper holidays(
            @Param("key") String key,
            @Param("country") String country,
            @Param("year") String year,
            @Param("month") String month
    );
}
