package panizio.DrivingSchool.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import panizio.DrivingSchool.validation.validator.CepValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CepValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCEP {
    String message() default "CEP inválido ou inexistente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}