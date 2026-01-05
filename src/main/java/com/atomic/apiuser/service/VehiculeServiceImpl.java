package com.atomic.apiuser.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.atomic.apiuser.domain.Vehicule;
import com.atomic.apiuser.entity.VehiculeEntity;
import com.atomic.apiuser.exceptions.VehiculeCreationException;
import com.atomic.apiuser.exceptions.VehiculeNotFoundException;
import com.atomic.apiuser.mapper.VehiculeEntityMapper;
import com.atomic.apiuser.repository.VehiculeRepository;

import jakarta.transaction.Transactional;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeEntityMapper vehiculeEntityMapper;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, VehiculeEntityMapper vehiculeEntityMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeEntityMapper = vehiculeEntityMapper;
    }

    @Override
    @Transactional
    public Vehicule save(Vehicule vehicule) {
        if (vehiculeRepository.findByPlate(vehicule.getPlate()).isPresent()) {
            throw new VehiculeCreationException("Vehicule with plate: " + vehicule.getPlate() + " is already used");
        }
        return vehiculeEntityMapper.toDomain(vehiculeRepository.save(vehiculeEntityMapper.toEntity(vehicule)));
    }

    @Override
    public Vehicule findById(UUID id) {
        return vehiculeRepository.findById(id).map(vehiculeEntityMapper::toDomain)
                .orElseThrow(() -> new VehiculeNotFoundException("Vehicule with id: " + id + " not found"));
    }

    @Override
    public Vehicule findByPlate(String plate) {
        return vehiculeRepository.findByPlate(plate).map(vehiculeEntityMapper::toDomain)
                .orElseThrow(() -> new VehiculeNotFoundException("Vehicule with plate: " + plate + " not found"));
    }

    @Override
    public List<Vehicule> findByBrand(String brand) {
        return vehiculeEntityMapper.toDomains(vehiculeRepository.findAllByBrand(brand));
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id)
                .orElseThrow(() -> new VehiculeNotFoundException("Vehicule not found"));

        vehiculeRepository.delete(vehiculeEntity);
    }

    @Transactional
    @Override
    public Vehicule update(UUID id, Vehicule vehicule) {
        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(id)
                .orElseThrow(() -> new VehiculeNotFoundException("Vehicule with id: " + id + " not found"));

        // Validar si la placa cambió y si la nueva placa ya existe en otro vehículo
        if (!vehiculeEntity.getPlate().equals(vehicule.getPlate())) {
            vehiculeRepository.findByPlate(vehicule.getPlate()).ifPresent(existing -> {
                throw new VehiculeCreationException("Vehicule with plate: " + vehicule.getPlate() + " is already used");
            });
        }

        vehiculeEntity.setBrand(vehicule.getBrand());
        vehiculeEntity.setModel(vehicule.getModel());
        vehiculeEntity.setPlate(vehicule.getPlate());
        vehiculeEntity.setYear(vehicule.getYear());
        vehiculeEntity.setFuelType(vehicule.getFuelType());
        vehiculeEntity.setOwner(vehicule.getOwner());

        return vehiculeEntityMapper.toDomain(vehiculeRepository.save(vehiculeEntity));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeEntityMapper.toDomains(vehiculeRepository.findAll());
    }
}
