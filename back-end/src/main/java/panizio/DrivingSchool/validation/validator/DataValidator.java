package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidData;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator implements ConstraintValidator<ValidData, String> {

    private String formato;

    @Override
    public void initialize(ValidData constraintAnnotation) {
        this.formato = constraintAnnotation.formato();
    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        if (data == null) {
            return true; // Ou false, dependendo da regra de negócio
        }

        try {
            // Tenta fazer o parsing da string para verificar se está no formato correto
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            formatter.parse(data); // Apenas valida o formato, sem converter para LocalDate
            return true;
        } catch (DateTimeParseException e) {
            return false; // Data inválida
        }
    }
}