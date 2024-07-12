package com.example.demo.exception;

/**
 * Exception thrown when attempting to add a vehicle with an ID that already exists.
 */
public class DuplicateVehicleException extends RuntimeException {

    /**
     * Constructs a new DuplicateVehicleException with a default error message.
     * The default message indicates that a vehicle with the same ID already exists.
     */
    public DuplicateVehicleException() {
        super("Vehicle with the same id already exists");
    }
}
