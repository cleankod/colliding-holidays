package cleankod.bs.holiday.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Holiday {
    private final String name;
    private final String country;
    private final LocalDate date;
}
