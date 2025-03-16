package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.CpfValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCPF {
  String message() default "CPF inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}