package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator implements ConstraintValidator<ValidData, LocalDate> {

    private String formato;

    @Override
    public void initialize(ValidData constraintAnnotation) {
        this.formato = constraintAnnotation.formato();
    }

    @Override
    public boolean isValid(LocalDate data, ConstraintValidatorContext context) {
        if (data == null) {
            return true; // Ou false, dependendo da regra de negócio
        }

        try {
            // Tenta formatar a data para garantir que está no formato correto
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            String dataFormatada = data.format(formatter);
            LocalDate.parse(dataFormatada, formatter); // Tenta fazer o parsing novamente
            return true;
        } catch (DateTimeParseException e) {
            return false; // Data inválida
        }
    }
}