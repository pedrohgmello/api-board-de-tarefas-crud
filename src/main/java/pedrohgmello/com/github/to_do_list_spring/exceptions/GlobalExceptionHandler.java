package pedrohgmello.com.github.to_do_list_spring.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRecursoNotException(RecursoNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ApiErrorResponse> handleRegraDeNegocioException(RegraDeNegocioException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                "Quebra de regra de negócio",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(AcessoNaoAutorizadoException.class)
    public ResponseEntity<ApiErrorResponse> handleAcessoNaoAutorizadoException(AcessoNaoAutorizadoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                "Acesso não autorizado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                "Erro inesperado",
                "Ocorreu erro inesperado no servidor",
                request.getRequestURI()
        );
        ex.printStackTrace();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
