package salemby.com.github.medVollApi.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import salemby.com.github.medVollApi.domain.exceptions.ValidationItemException;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(dataErrorValidation::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidationItemException.class)
    public ResponseEntity validationError(ValidationItemException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }




    private record dataErrorValidation(String field, String message){
        public dataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
