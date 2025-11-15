package com.aitrain.users.domain.exceptions;

public class ValoracionYaExisteException extends RuntimeException {
    public ValoracionYaExisteException(String message) {
        super(message);
    }
}
