package com.caching.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom runtime exception class to represent invalid input provided.
 */
@AllArgsConstructor
@Data
public class InvalidInputProvidedException extends RuntimeException {

    /**
     * The error message associated with the invalid input.
     */
    private final String message;
}
