package panizio.DrivingSchool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para erros de validação (ex: @NotBlank, @Size, @Pattern)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Um ou mais campos estão inválidos",
                request.getDescription(false).replace("uri=", ""), // Captura o caminho real da requisição
                errors // Detalhes dos erros de validação
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Tratamento para recursos duplicados
    @ExceptionHandler(DuplicateData.class)
    public ResponseEntity<ErrorResponse> handleResourceDuplicateData(DuplicateData ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(), // Use 409 para conflitos de dados
                "Recursos duplicados encontrados",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                ex.getCamposDuplicados() // Adiciona os campos duplicados à resposta
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Tratamento para erros genéricos (ex: NullPointerException,
    // IllegalArgumentException)
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    //     ErrorResponse response = new ErrorResponse(
    //             HttpStatus.INTERNAL_SERVER_ERROR.value(),
    //             "Erro interno",
    //             "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
    //             "/clientes" // Substitua pelo caminho real da requisição
    //     );

    //     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}