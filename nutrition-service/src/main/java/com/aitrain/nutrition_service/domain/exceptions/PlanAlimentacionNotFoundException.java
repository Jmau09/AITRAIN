package com.aitrain.nutrition_service.domain.exceptions;

public class PlanAlimentacionNotFoundException extends RuntimeException {
    public PlanAlimentacionNotFoundException(String message) {
        super(message);
    }
}
