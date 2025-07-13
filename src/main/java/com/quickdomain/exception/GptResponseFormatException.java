package com.quickdomain.exception;

public class GptResponseFormatException extends RuntimeException {
    public GptResponseFormatException(String message) {
        super(message);
    }
}
