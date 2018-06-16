package cleankod.bs.holiday.client.domain;

import java.time.Duration;

import lombok.Getter;

@Getter
public class Timeout {
    private final long value;

    public Timeout(String duration) {
        this.value = Duration.parse(duration).toMillis();
    }
}
