package com.atomic.apiuser.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atomic.apiuser.entity.VehiculeEntity;

public interface VehiculeRepository extends JpaRepository<VehiculeEntity, UUID> {
    Optional<VehiculeEntity> findByPlate(String plate);
    List<VehiculeEntity> findAllByBrand(String brand);
}
