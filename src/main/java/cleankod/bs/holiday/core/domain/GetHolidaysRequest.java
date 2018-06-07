package cleankod.bs.holiday.core.domain;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetHolidaysRequest {
    private final List<String> countries;
    private final String date;
}
