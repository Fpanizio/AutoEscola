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

    // Captura erros de validação dos @Valid em controllers
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Erro de validação");
        response.setErrors(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Captura erros de validação em outros contextos (ex: consultas do banco)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Erro de validação");
        response.setErrors(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Captura erros de violação de chave única no banco de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = "Erro de integridade de dados: violação de chave única";

        // Verifica se a violação de integridade está associada a campos específicos,
        // como CPF, RG ou CNH
        if (ex.getMessage().contains("cpf")) {
            errorMessage = "Já existe um usuário cadastrado com este CPF.";
        } else if (ex.getMessage().contains("rg")) {
            errorMessage = "Já existe um usuário cadastrado com este RG.";
        } else if (ex.getMessage().contains("numero_cnh")) {
            errorMessage = "Já existe um usuário cadastrado com este número de CNH.";
        }

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.CONFLICT.value()); // 409 - Conflito
        response.setMessage(errorMessage);
        response.setErrors(List.of(ex.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Outros handlers (ex: Exception genérica) podem ser adicionados aqui
}