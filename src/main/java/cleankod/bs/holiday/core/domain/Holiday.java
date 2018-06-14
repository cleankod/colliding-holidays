package cleankod.bs.holiday.core.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Holiday implements Serializable {
    private final String name;
    private final String country;
    private final LocalDate date;
}
