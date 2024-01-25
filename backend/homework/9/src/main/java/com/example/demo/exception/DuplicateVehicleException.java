package com.example.demo.exception;

public class DuplicateVehicleException extends RuntimeException {

    /**
     * Constructs a new DuplicateVehicleException with a default error message.
     * The default message indicates that a vehicle with the same ID already exists.
     */
    public DuplicateVehicleException() {
        super("Vehicle with the same id already exists");
    }
}
