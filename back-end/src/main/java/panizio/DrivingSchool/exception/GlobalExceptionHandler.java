package panizio.DrivingSchool.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handler único para erros de validação de DTOs (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handler para erros de validação em nível de banco/consultas
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Violação de regras",
                errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = "Erro de integridade de dados";
        String errorDetail = ex.getMostSpecificCause().getMessage();

        // Extrai o nome do campo da mensagem de erro
        String campo = extrairCampoDaMensagem(errorDetail);

        if (campo != null) {
            errorMessage = "Já existe um registro com o mesmo valor para o campo: " + campo;
        }

        ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                errorMessage,
                List.of(errorDetail));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Handler para funcionário não encontrado
    @ExceptionHandler(notFoundEmployleesException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundEmployleesException(notFoundEmployleesException ex) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                List.of());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private String extrairCampoDaMensagem(String mensagem) {
        if (mensagem.contains("Detail: Key (")) {
            int start = mensagem.indexOf("Detail: Key (") + "Detail: Key (".length();
            int end = mensagem.indexOf(")", start);
            return mensagem.substring(start, end);
        }
        return null;
    }
}