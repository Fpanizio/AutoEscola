package panizio.DrivingSchool.utils;

import panizio.DrivingSchool.exception.CpfInvalidURL;

public class CpfUtils {

  public static String formatarCpf(String cpf) {
    if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
      throw new CpfInvalidURL("CPF inválido. Deve conter 11 dígitos.");
    }
    return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
  }
}