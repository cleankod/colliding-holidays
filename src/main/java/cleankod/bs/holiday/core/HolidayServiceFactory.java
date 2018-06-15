package cleankod.bs.holiday.core;

public class HolidayServiceFactory {
    public static CollidingHolidayService create(HolidayForSingleCountryFetcher holidayForSingleCountryFetcher) {
        return new CollidingHolidayService(new HolidayPerCountryFetcher(holidayForSingleCountryFetcher));
    }
}
