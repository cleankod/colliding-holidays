package cleankod.bs.holiday.core.domain;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupportedCountriesValidator implements ConstraintValidator<SupportedCountries, List<String>> {

   private final SupportedCountriesWrapper supportedCountriesWrapper;

   public void initialize(SupportedCountries constraint) {
   }

   public boolean isValid(List<String> countries, ConstraintValidatorContext context) {
      if (countries == null || countries.isEmpty()) {
         return true;
      }

      return supportedCountriesWrapper.getCountries().containsAll(countries);
   }
}
