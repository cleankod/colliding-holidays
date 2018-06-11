package cleankod.bs.holiday.core.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SupportedCountriesValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface SupportedCountries {
    String message() default "{validation.unsupported-countries}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
