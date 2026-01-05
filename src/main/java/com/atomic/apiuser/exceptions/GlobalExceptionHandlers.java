package com.atomic.apiuser.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.atomic.apiuser.error.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    
    @ExceptionHandler(VehiculeNotFoundException.class)
    public ResponseEntity<ApiError> handleVehiculeNotFoundException(VehiculeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError("not_found", ex.getMessage()));
    }

    @ExceptionHandler(VehiculeCreationException.class)
    public ResponseEntity<ApiError> handleVehiculeCreationException(VehiculeCreationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError("bad_request", ex.getMessage()));
    }

    // esta excepcion es para errores no controlados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("internal_server_error", ex.getMessage()));
    }

}
