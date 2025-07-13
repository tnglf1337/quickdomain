package com.quickdomain.exception;

/**
 * Exception thrown when the response content from an API does not match the expected format.
 * This can occur if the provider gpt interprets the built prompt wrong.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class InvalidResponseContentException extends RuntimeException {
    public InvalidResponseContentException(String message) {
        super(message);
    }
}
