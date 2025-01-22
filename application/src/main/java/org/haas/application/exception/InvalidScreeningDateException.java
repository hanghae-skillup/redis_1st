package org.haas.application.exception;

public class InvalidScreeningDateException extends RuntimeException {
    public InvalidScreeningDateException(String message) {
        super(message);
    }
}
