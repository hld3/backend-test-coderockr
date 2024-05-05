package com.dodson.backendcoderockr.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger for logging exceptions.
     */
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Catches any request validation errors.
     * @param exception the {@MethodArgumentNotValidException}.
     * @return {@ResponseEntity} with the validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>> requestValidationError(
            final MethodArgumentNotValidException exception) {
        logger.error("There was a request validation error");
        Map<String, String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream().collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
