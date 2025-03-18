package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidTelefone;

import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<ValidTelefone, String> {

  private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\+\\d{2} \\(\\d{2}\\) \\d{4,5}-\\d{4}$");

  @Override
  public boolean isValid(String telefone, ConstraintValidatorContext context) {
    if (telefone == null) {
      return false; // ou true, dependendo se você quer permitir valores nulos
    }
    return TELEFONE_PATTERN.matcher(telefone).matches();
  }
}