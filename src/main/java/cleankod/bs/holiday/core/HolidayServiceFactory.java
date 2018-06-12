package cleankod.bs.holiday.core;

import cleankod.bs.holiday.client.HolidayClient;

public class HolidayServiceFactory {
    public static HolidayService getInstance(HolidayClient holidayClient) {
        return new HolidayService(new HolidayPerCountryFetcher(new HolidayForSingleCountryFetcher(holidayClient)));
    }
}
