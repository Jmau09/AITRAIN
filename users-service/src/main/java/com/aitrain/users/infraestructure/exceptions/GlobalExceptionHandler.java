package com.aitrain.users.infraestructure.exceptions;

import com.aitrain.users.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailEmptyException.class)
    public ResponseEntity<String> handleEmailEmptyException(EmailEmptyException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<String> handleCredencialesIncorrectas(IncorrectCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFoundException(AdminNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ex.getMessage());
    }

    // Manejo del error: email ya registrado
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailExists(EmailAlreadyExistException ex) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ex.getMessage());
    }
    @ExceptionHandler(ValoracionNotFoundException.class)
    public ResponseEntity<String> handleValoracionNotFound(ValoracionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(ValoracionInvalidDataException.class)
    public ResponseEntity<String> handleValoracionInvalid(ValoracionInvalidDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(ValoracionYaExisteException.class)
    public ResponseEntity<String> handleValoracionInvalid(ValoracionYaExisteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }


    // Manejo de validaciones @Valid si en el futuro usas anotaciones de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Manejo genérico para cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body("Error interno en el servidor: " + ex.getMessage());
    }
}
