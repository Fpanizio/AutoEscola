package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidRG;
import java.util.regex.Pattern;

public class RgValidator implements ConstraintValidator<ValidRG, String> {

  private static final Pattern RG_PATTERN = Pattern.compile("^[0-9]{1,2}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9Xx]{1}$");

  @Override
  public boolean isValid(String rg, ConstraintValidatorContext context) {
    if (rg == null) {
      return false;
    }
    return RG_PATTERN.matcher(rg).matches();
  }
}