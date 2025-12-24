package com.security.authservice.manager.exception;

public class SenhasNaoCoincidemException extends RuntimeException {
    public SenhasNaoCoincidemException(final String message) {
        super(message);
    }
}
