package com.subjecttochange.ghhelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {


    public BadRequestException() {
        super();
    }

    /**
     * @param message to display to client
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * @param message to display to client
     * @param cause of the 400 error
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}