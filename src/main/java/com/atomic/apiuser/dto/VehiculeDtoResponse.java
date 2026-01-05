package com.atomic.apiuser.dto;

import java.util.UUID;

public record VehiculeDtoResponse(
        UUID id,
        String brand,
        String model,
        String plate,
        Integer year,
        String fuelType,
        String owner) {

}
