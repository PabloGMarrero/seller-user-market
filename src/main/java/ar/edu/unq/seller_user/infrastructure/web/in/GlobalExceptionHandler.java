package ar.edu.unq.seller_user.infrastructure.web.in;

import ar.edu.unq.seller_user.application.exceptions.EmailAlreadyInUseException;
import ar.edu.unq.seller_user.application.exceptions.ElementNotFoundException;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.error.GenericErrorResponseDTO;
import ar.edu.unq.seller_user.infrastructure.web.in.dto.error.ValidationErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleEmailAlreadyInUseException(EmailAlreadyInUseException emailAlreadyInUseException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(emailAlreadyInUseException.getMessage()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleUserNotFoundException(ElementNotFoundException elementNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(elementNotFoundException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponseDTO(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorResponseDTO> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericErrorResponseDTO(exception.getMessage()));
    }
}
