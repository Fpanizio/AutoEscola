package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.RgValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RgValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRG {
  String message() default "RG inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}