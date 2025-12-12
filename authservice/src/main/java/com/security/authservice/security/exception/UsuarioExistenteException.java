package com.security.authservice.security.exception;

public class UsuarioExistenteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsuarioExistenteException(final String message) {
        super(message);
    }
}
