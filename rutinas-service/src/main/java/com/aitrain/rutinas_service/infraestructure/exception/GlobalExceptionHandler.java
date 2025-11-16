package com.aitrain.rutinas_service.infraestructure.exception;

import com.aitrain.rutinas_service.domain.exceptions.*;
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

    // ---- RUTINA ----
    @ExceptionHandler(RutinaNotFoundException.class)
    public ResponseEntity<String> handleRutinaNotFound(RutinaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RutinaInvalidDataException.class)
    public ResponseEntity<String> handleRutinaInvalid(RutinaInvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // ---- DIA RUTINA ----
    @ExceptionHandler(DiaRutinaNotFound.class)
    public ResponseEntity<String> handleDiaRutinaNotFound(DiaRutinaNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DiaRutinaInvalidDataException.class)
    public ResponseEntity<String> handleDiaRutinaInvalid(DiaRutinaInvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // ---- EJERCICIO ----
    @ExceptionHandler(EjercicioNotFoundException.class)
    public ResponseEntity<String> handleEjercicioNotFound(EjercicioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EjercicioInvalidDataException.class)
    public ResponseEntity<String> handleEjercicioInvalid(EjercicioInvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    // ---- VALIDACIONES (@Valid) ----
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


    // ---- EXCEPCIÓN GENERAL ----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + ex.getMessage());
    }
}