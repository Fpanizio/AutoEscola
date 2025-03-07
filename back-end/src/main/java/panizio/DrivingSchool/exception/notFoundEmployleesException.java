package panizio.DrivingSchool.exception;

public class notFoundEmployleesException extends RuntimeException {

  public notFoundEmployleesException(String cpf) {
    super("Funcionário não encontrado com o CPF: " + cpf);
  }
}
