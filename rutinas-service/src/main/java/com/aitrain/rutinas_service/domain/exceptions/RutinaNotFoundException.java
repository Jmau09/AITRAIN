package com.aitrain.rutinas_service.domain.exceptions;

public class RutinaNotFoundException extends RuntimeException {
    public RutinaNotFoundException(String message) {
        super(message);
    }
}
