package cleankod.bs.holiday.core.domain;

import java.time.Clock;
import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupportedDateValidator implements ConstraintValidator<SupportedDate, LocalDate> {

   private static final int FIRST_DAY_OF_MONTH = 1;
   private final boolean onlyPastSupported;
   private final Clock clock;

   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
      if (date == null) {
         return true;
      }

      if (onlyPastSupported) {
         return date.isBefore(LocalDate.now(clock).withDayOfMonth(FIRST_DAY_OF_MONTH));
      }

      return true;
   }
}
