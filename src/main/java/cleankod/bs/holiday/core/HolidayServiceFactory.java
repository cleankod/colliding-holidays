package cleankod.bs.holiday.core;

public class HolidayServiceFactory {
    public static HolidayService create(HolidayForSingleCountryFetcher holidayForSingleCountryFetcher) {
        return new HolidayService(new HolidayPerCountryFetcher(holidayForSingleCountryFetcher));
    }
}
