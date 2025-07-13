package com.quickdomain.exception;

/**
 * Exception thrown when the response from GPT does not match the expected format.
 * This can occur if the response is malformed or does not contain the expected data structure most likely when the provider did not answer with Http status 200.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class GptResponseFormatException extends RuntimeException {
    public GptResponseFormatException(String message) {
        super(message);
    }
}
