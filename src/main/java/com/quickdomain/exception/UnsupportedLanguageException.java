package com.quickdomain.exception;

/**
 * Exception thrown when a language for the prompt is not supported.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class UnsupportedLanguageException extends RuntimeException {
    public UnsupportedLanguageException(String message) {
        super(message);
    }
}
