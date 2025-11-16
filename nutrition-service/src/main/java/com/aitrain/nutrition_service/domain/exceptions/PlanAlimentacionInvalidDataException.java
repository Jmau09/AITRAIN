package com.aitrain.nutrition_service.domain.exceptions;

public class PlanAlimentacionInvalidDataException extends RuntimeException {
    public PlanAlimentacionInvalidDataException(String message) {
        super(message);
    }
}
