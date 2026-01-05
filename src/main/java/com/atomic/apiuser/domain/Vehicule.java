package com.atomic.apiuser.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Vehicule {
    private UUID id;
    private String brand;
    private String model;
    private String plate;
    private Integer year;
    private String fuelType;
    private String owner;
}
