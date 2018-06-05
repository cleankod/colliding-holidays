package cleankod.bs.holiday;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("/countries")
class CountryController {

    @GetMapping
    public CountryListWrapper get() {
        return new CountryListWrapper(List.of("PL", "NO"));
    }

    @Data
    static class CountryListWrapper {
        private final List<String> countries;
    }
}
