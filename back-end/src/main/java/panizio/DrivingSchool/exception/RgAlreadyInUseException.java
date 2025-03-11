package panizio.DrivingSchool.exception;

public class RgAlreadyInUseException extends RuntimeException {
    public RgAlreadyInUseException(String message) {
        super(message);
    }
}