package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.AdultValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AdultValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdult {
    String message() default "O cliente deve ser maior de 18 anos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}