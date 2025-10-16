package digital.rj.apicadastrodecursos.infra.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //  Tratamento específico para erros de recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request, ex);
    }

    //  Tratamento de erros de conflito (duplicidade, etc)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex, WebRequest request) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), request, ex);
    }

    //  Tratamento de erros de requisição inválida
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex, WebRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request, ex);
    }

    //  Falhas de autenticação/autorização
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        return buildError(HttpStatus.FORBIDDEN, "Access denied: " + ex.getMessage(), request, ex);
    }

    //  Erros de validação de campos @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + " " + err.getDefaultMessage())
                .orElse("Validation error");
        return buildError(HttpStatus.BAD_REQUEST, message, request, ex);
    }

    // Fallback genérico para qualquer erro não tratado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex, WebRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + ex.getMessage(), request, ex);
    }

    // Método centralizado para montar e logar o erro
    private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message, WebRequest request, Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));

        // Log colorido (melhor visual no terminal)
        String color;
        if (status.is4xxClientError()) color = "\u001B[33m"; // amarelo
        else if (status.is5xxServerError()) color = "\u001B[31m"; // vermelho
        else color = "\u001B[36m"; // azul claro

        log.error(color + "[{}] {} - {}: {}", status.value(), status.getReasonPhrase(), request.getDescription(false), ex.getMessage() + "\u001B[0m");

        return ResponseEntity.status(status).body(body);
    }
}