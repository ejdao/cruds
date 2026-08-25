package ejdao.backend.shared.presentation.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleResponseStatusException(
    ResponseStatusException exception,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
    String message = exception.getReason();

    if (message == null || message.isBlank()) {
      message = status.getReasonPhrase();
    }

    return buildResponse(status, message, request);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
    MethodArgumentNotValidException exception,
    HttpServletRequest request
  ) {
    String message = exception
      .getBindingResult()
      .getFieldErrors()
      .stream()
      .findFirst()
      .map(fieldError -> fieldError.getDefaultMessage())
      .orElse("Validation failed.");

    return buildResponse(HttpStatus.BAD_REQUEST, message, request);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
    DataIntegrityViolationException exception,
    HttpServletRequest request
  ) {
    String databaseMessage = getMostSpecificMessage(exception);
    String message = "Data integrity violation.";

    if (databaseMessage.toLowerCase().contains("unique")) {
      message = "A record with the same unique value already exists.";
    }

    return buildResponse(HttpStatus.CONFLICT, message, request);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ErrorResponse> handleDataAccessException(
    DataAccessException exception,
    HttpServletRequest request
  ) {
    return buildResponse(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Database operation failed.",
      request
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
    Exception exception,
    HttpServletRequest request
  ) {
    return buildResponse(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Unexpected server error.",
      request
    );
  }

  private ResponseEntity<ErrorResponse> buildResponse(
    HttpStatus status,
    String message,
    HttpServletRequest request
  ) {
    return ResponseEntity.status(status).body(
      new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        message,
        request.getRequestURI()
      )
    );
  }

  private String getMostSpecificMessage(Exception exception) {
    Throwable cause = NestedExceptionUtils.getMostSpecificCause(exception);

    if (cause == null || cause.getMessage() == null) {
      return exception.getMessage();
    }

    return cause.getMessage();
  }
}
