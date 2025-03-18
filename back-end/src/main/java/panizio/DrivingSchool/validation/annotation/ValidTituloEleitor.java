package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.TituloEleitorValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TituloEleitorValidator.class)
public @interface ValidTituloEleitor {
  String message() default "Título de Eleitor inválido - Espera um template: XXXX.XXXX.XXXX";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
