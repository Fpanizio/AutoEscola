package panizio.DrivingSchool.utils;

import panizio.DrivingSchool.exception.VeiculosException;

public class VeiculosUtils {

  public static String formatarPlaca(String placa) {
    if (placa == null || placa.isBlank()) {
      throw new VeiculosException("A placa não pode ser nula ou vazia.");
    }
    return placa.trim().toUpperCase();
  }
}
