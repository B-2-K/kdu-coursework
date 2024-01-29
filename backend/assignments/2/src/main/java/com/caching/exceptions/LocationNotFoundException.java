package com.caching.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Custom runtime exception class to represent a location not found.
 */
@AllArgsConstructor
@Data
public class LocationNotFoundException extends RuntimeException {

    /**
     * The error message associated with the location not being found.
     */
    private final String message;
}
