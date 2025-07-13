package com.quickdomain.exception;

public class InvalidResponseContentException extends RuntimeException {
    public InvalidResponseContentException(String message) {
        super(message);
    }
}
