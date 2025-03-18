package panizio.DrivingSchool.validation.validator;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidTituloEleitor;

public class TituloEleitorValidator implements ConstraintValidator<ValidTituloEleitor, String> {
    // Expressão regular para validar o formato 1234.5678.0123
    private static final Pattern TITULO_PATTERN = Pattern.compile("^\\d{4}\\.\\d{4}\\.\\d{4}$");

    @Override
    public boolean isValid(String titulo, ConstraintValidatorContext context) {
        if (titulo == null) {
            return false; // Evita valores nulos
        }
        return TITULO_PATTERN.matcher(titulo).matches();
    }
}
