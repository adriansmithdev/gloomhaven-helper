package com.subjecttochange.ghhelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author SubjectToChange
 * @version 1
 *
 * Responds with a 404 error stating that the resource was not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
     * @param message to display to client
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * @param message to display to client
     * @param cause of the 404 error
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}