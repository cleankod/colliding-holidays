package cleankod.bs.holiday.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cleankod.bs.holiday.core.domain.SupportedCountriesWrapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class CountryController {

    private final SupportedCountriesWrapper supportedCountriesWrapper;

    @GetMapping
    public SupportedCountriesWrapper get() {
        return supportedCountriesWrapper;
    }

}
