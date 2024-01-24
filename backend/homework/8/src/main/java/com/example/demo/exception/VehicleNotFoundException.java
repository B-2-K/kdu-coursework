package com.example.demo.exception;

/**
 * Exception thrown when attempting to access or manipulate a vehicle that cannot be found.
 */
public class VehicleNotFoundException extends RuntimeException {

    /**
     * Constructs a new VehicleNotFoundException with a default error message.
     * The default message indicates that the vehicle could not be found.
     */
    public VehicleNotFoundException() {
        super("Vehicle not found");
    }
}
