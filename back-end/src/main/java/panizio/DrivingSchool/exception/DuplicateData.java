package panizio.DrivingSchool.exception;

import java.util.HashMap;
import java.util.Map;

public class DuplicateData extends RuntimeException {
  private final Map<String, String> camposDuplicados;

  public DuplicateData(String message) {
    super(message);
    this.camposDuplicados = new HashMap<>();
  }

  public DuplicateData(String message, Map<String, String> camposDuplicados) {
    super(message);
    this.camposDuplicados = camposDuplicados;
  }

  public Map<String, String> getCamposDuplicados() {
    return camposDuplicados;
  }
}