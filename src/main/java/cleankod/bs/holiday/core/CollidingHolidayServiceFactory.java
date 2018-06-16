package cleankod.bs.holiday.core;

public class CollidingHolidayServiceFactory {
    public static CollidingHolidayService create(HolidayForSingleCountryFetcher holidayForSingleCountryFetcher) {
        return new CollidingHolidayService(new HolidayPerCountryFetcher(holidayForSingleCountryFetcher));
    }
}
