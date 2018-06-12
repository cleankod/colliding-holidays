package cleankod.bs.holiday.client.domain;

import java.util.regex.Pattern;

import lombok.Getter;

@Getter
public class ApiKey {
    private static final String KEY_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    private final String value;

    public ApiKey(String value) {
        Pattern p = Pattern.compile(KEY_PATTERN);
        if (value == null || !p.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid API key format!");
        }
        this.value = value;
    }
}
