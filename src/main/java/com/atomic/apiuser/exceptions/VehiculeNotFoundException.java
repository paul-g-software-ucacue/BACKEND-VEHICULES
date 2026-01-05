package com.atomic.apiuser.exceptions;

public class VehiculeNotFoundException extends RuntimeException {
    public VehiculeNotFoundException(String message) {
        super(message);
    }
}
