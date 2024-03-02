package com.assignment.Assignment.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(BindException ex) {
        String errorMessage = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Validation failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> handleAccessDenied(Exception ex, WebRequest requset, HttpServletResponse res) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", "403");
        errors.put("errorMessage", "Access Denied ");
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> handleIllegalArgumentException(Exception ex, WebRequest requset, HttpServletResponse res) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", "400");
        errors.put("errorMessage", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleBadCredentialsException(Exception ex, WebRequest requset, HttpServletResponse res) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", "401");
        errors.put("errorMessage", "Incorrect username or password ");
        return errors;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAllExceptionMethod(Exception ex, WebRequest requset, HttpServletResponse res) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", "500");
        errors.put("errorMessage", "SOMETHING WRONG ");
        return errors;
    }
}
