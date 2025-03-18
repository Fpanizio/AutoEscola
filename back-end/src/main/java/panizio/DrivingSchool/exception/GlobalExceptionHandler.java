package panizio.DrivingSchool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
                                request.getDescription(false).replace("uri=", ""), // Captura o caminho real da
                                                                                   // requisição
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

        // Tratamento para erros de formatação de data
        @ExceptionHandler(DateTimeParseException.class)
        public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex,
                        WebRequest request) {
                ErrorResponse response = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                "Erro de formatação de data",
                                "A data fornecida está em um formato inválido. Use o formato yyyy-MM-dd.",
                                request.getDescription(false).replace("uri=", ""),
                                null // Não há campos específicos para este erro
                );

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Tratamento para ENUMS inválidos
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
                        WebRequest request) {
                String errorMessage = "Erro ao processar a requisição JSON.";

                // Verifica se a exceção foi causada por um erro de desserialização de enum
                if (ex.getCause() instanceof InvalidFormatException) {
                        InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
                        if (invalidFormatException.getTargetType() != null
                                        && invalidFormatException.getTargetType().isEnum()) {
                                errorMessage = String.format("Valor inválido para o campo '%s'. Valores aceitos: %s",
                                                invalidFormatException.getPath().get(0).getFieldName(),
                                                Arrays.toString(invalidFormatException.getTargetType()
                                                                .getEnumConstants()));
                        }
                }

                ErrorResponse response = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                "Erro de desserialização",
                                errorMessage,
                                request.getDescription(false).replace("uri=", ""),
                                null);

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Tratamento para recursos não encontrados (NotFoundData)
        @ExceptionHandler(NotFoundData.class)
        public ResponseEntity<ErrorResponse> handleNotFoundData(NotFoundData ex, WebRequest request) {
                ErrorResponse response = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(), // Status 404 Not Found
                                "Recurso não encontrado",
                                ex.getMessage(),
                                request.getDescription(false).replace("uri=", ""),
                                null // Não há campos específicos para este erro
                );

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Tratamento para CPF na URl inválido
        @ExceptionHandler(CpfInvalidURL.class)
        public ResponseEntity<ErrorResponse> handleCpfInvalidURL(CpfInvalidURL ex, WebRequest request) {
                ErrorResponse response = new ErrorResponse(
                                HttpStatus.NOT_FOUND.value(), // Status 404 Not Found
                                "CPF inválido",
                                ex.getMessage(),
                                request.getDescription(false).replace("uri=", ""),
                                null // Não há campos específicos para este erro
                );

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Tratamento para erros genéricos (ex: NullPointerException,
        // IllegalArgumentException)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
                ErrorResponse response = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Erro interno",
                                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.",
                                request.getDescription(false).replace("uri=", ""),
                                null);

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}