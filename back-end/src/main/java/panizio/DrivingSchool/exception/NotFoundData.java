package panizio.DrivingSchool.exception;

public class NotFoundData extends RuntimeException {
  public NotFoundData(String mensagem) {
    super(mensagem);
  }
}
