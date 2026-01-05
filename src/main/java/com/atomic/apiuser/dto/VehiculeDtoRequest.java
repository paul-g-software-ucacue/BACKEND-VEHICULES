package com.atomic.apiuser.dto;

public record VehiculeDtoRequest(
    String brand,
    String model,
    String plate,
    Integer year,
    String fuelType,
    String owner
) {
    
}
