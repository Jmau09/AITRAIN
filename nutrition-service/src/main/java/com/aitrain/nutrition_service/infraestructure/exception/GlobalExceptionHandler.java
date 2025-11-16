package com.aitrain.nutrition_service.infraestructure.exception;

import com.aitrain.nutrition_service.domain.exceptions.PlanAlimentacionInvalidDataException;
import com.aitrain.nutrition_service.domain.exceptions.PlanAlimentacionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlanAlimentacionNotFoundException.class)
    public ResponseEntity<String> handlePlanNotFound(PlanAlimentacionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    @ExceptionHandler(PlanAlimentacionInvalidDataException.class)
    public ResponseEntity<String> handlePlanNotFound(PlanAlimentacionInvalidDataException ex) {
        return ResponseEntity.status(HttpStatus.OK).body(ex.getMessage());
    }

    // puedes agregar más excepciones personalizadas aquí
}