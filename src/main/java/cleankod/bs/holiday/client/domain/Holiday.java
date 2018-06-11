package cleankod.bs.holiday.client.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Holiday {
    private final String name;
    private final LocalDate date;
}
