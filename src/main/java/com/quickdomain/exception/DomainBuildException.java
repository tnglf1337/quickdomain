package com.quickdomain.exception;

/**
 * Exception thrown when there is an error during the construction of a domain entity.
 * This can occur if the constructor parameters do not match the expected types.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class DomainBuildException extends RuntimeException {
    public DomainBuildException(String message) {
        super(message);
    }
}
