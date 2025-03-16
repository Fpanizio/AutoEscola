package panizio.DrivingSchool.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import panizio.DrivingSchool.validation.annotation.ValidCPF;

public class CpfValidator implements ConstraintValidator<ValidCPF, String> {

  @Override
  public boolean isValid(String cpf, ConstraintValidatorContext context) {
    if (cpf == null || cpf.isEmpty())
      return false;

    // Remove caracteres não numéricos
    cpf = cpf.replaceAll("[^0-9]", "");

    if (cpf.length() != 11)
      return false;

    // Verifica dígitos repetidos (ex: 111.111.111-11)
    if (cpf.matches("(\\d)\\1{10}"))
      return false;

    // Cálculo do primeiro dígito verificador
    int soma = 0;
    for (int i = 0; i < 9; i++) {
      soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
    }
    int digito1 = 11 - (soma % 11);
    if (digito1 > 9)
      digito1 = 0;

    // Cálculo do segundo dígito verificador
    soma = 0;
    for (int i = 0; i < 10; i++) {
      soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
    }
    int digito2 = 11 - (soma % 11);
    if (digito2 > 9)
      digito2 = 0;

    // Compara com os dígitos informados
    return (Character.getNumericValue(cpf.charAt(9)) == digito1)
        && (Character.getNumericValue(cpf.charAt(10)) == digito2);
  }
}