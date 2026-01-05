package com.atomic.apiuser.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_vehicules")
public class VehiculeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String brand;
    @Column(nullable = false, length = 100)
    private String model;
    @Column(nullable = false, unique = true, length = 20)
    private String plate;
    @Column(nullable = false, name = "manufacture_year")
    private Integer year;
    @Column(nullable = false, length = 50)
    private String fuelType;
    @Column(nullable = false, length = 150)
    private String owner;
}
