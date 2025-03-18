package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.DataValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataValidator.class) // Validador associado
public @interface ValidData {
  String message() default "Data inválida";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String formato() default "yyyy-MM-dd"; // Parâmetro opcional para o formato
}