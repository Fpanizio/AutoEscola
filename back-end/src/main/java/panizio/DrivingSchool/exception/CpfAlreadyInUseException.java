package panizio.DrivingSchool.exception;

public class CpfAlreadyInUseException extends RuntimeException {
    public CpfAlreadyInUseException(String message) {
        super(message);
    }
}