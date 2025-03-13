package panizio.DrivingSchool.exception;

public class notFoundClientException extends RuntimeException {
  
  public notFoundClientException(String cpf) {
    super("Cliente não encontrado com o CPF: " + cpf);
  }
  
}
