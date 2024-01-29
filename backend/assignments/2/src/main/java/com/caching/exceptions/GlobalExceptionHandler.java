package com.caching.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling specific and generic exceptions in the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type LocationNotFoundException.
     *
     * @param e The LocationNotFoundException instance.
     * @return ResponseEntity with a BAD_REQUEST status and the exception message.
     */
    @ExceptionHandler(value = LocationNotFoundException.class)
    public ResponseEntity<String> handleCustomException(LocationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Handles exceptions of type InvalidInputProvidedException.
     *
     * @param e The InvalidInputProvidedException instance.
     * @return ResponseEntity with an EXPECTATION_FAILED status and the exception message.
     */
    @ExceptionHandler(value = InvalidInputProvidedException.class)
    public ResponseEntity<String> invalidInputExceptionHandling(InvalidInputProvidedException e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }

    /**
     * Handles generic exceptions of type Exception.
     *
     * @param e The Exception instance.
     * @return ResponseEntity with an INTERNAL_SERVER_ERROR status and a generic error message.
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> genericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }
}
