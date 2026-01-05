package com.atomic.apiuser.service;

import java.util.List;
import java.util.UUID;

import com.atomic.apiuser.domain.Vehicule;

public interface VehiculeService {
    Vehicule save(Vehicule vehicule);

    Vehicule findById(UUID id);

    Vehicule findByPlate(String plate);

    List<Vehicule> findByBrand(String brand);

    void delete(UUID id);

    Vehicule update(UUID id, Vehicule vehicule);

    List<Vehicule> findAll();

}
