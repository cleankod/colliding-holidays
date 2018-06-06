package cleankod.bs.holiday.gateway.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Holiday {
    private final String name;
    private final LocalDate date;
}
