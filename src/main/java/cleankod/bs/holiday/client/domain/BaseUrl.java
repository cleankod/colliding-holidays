package cleankod.bs.holiday.client.domain;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import lombok.Getter;

@Getter
public class BaseUrl {
    private static final String[] SUPPORTED_PROTOCOLS = {"http", "https"};
    private final String value;

    public BaseUrl(String value) {
        try {
            URL url = new URL(value);
            if (!Arrays.asList(SUPPORTED_PROTOCOLS).contains(url.getProtocol())) {
                throw new IllegalArgumentException("Invalid base URL format - unsupported protocol!");
            }

            if (url.getHost().isEmpty()) {
                throw new IllegalArgumentException("Invalid base URL format - unknown host!");
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid base URL format!");
        }
        this.value = value;
    }
}
