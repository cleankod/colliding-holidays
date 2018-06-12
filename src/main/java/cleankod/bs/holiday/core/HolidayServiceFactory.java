package cleankod.bs.holiday.core;

public class HolidayServiceFactory {
    public static HolidayService getInstance(HolidayForSingleCountryFetcher holidayForSingleCountryFetcher) {
        return new HolidayService(new HolidayPerCountryFetcher(holidayForSingleCountryFetcher));
    }
}
