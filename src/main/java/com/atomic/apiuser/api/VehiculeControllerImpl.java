package com.atomic.apiuser.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.atomic.apiuser.api.controller.VehiculeController;
import com.atomic.apiuser.dto.VehiculeDtoRequest;
import com.atomic.apiuser.dto.VehiculeDtoResponse;
import com.atomic.apiuser.mapper.VehiculeDtoMapper;
import com.atomic.apiuser.service.VehiculeService;

@RestController
public class VehiculeControllerImpl implements VehiculeController {

    private final VehiculeService vehiculeService;
    private final VehiculeDtoMapper vehiculeDtoMapper;

    public VehiculeControllerImpl(VehiculeService vehiculeService, VehiculeDtoMapper vehiculeDtoMapper) {
        this.vehiculeService = vehiculeService;
        this.vehiculeDtoMapper = vehiculeDtoMapper;
    }

    @Override
    public ResponseEntity<VehiculeDtoResponse> save(VehiculeDtoRequest vehiculeDtoRequest) {
        return new ResponseEntity<>(vehiculeDtoMapper.toResponse(vehiculeService.save(vehiculeDtoMapper.toDomain(vehiculeDtoRequest))),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<VehiculeDtoResponse> findById(UUID id) {
        return new ResponseEntity<>(vehiculeDtoMapper.toResponse(vehiculeService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<VehiculeDtoResponse> findByPlate(String plate) {
        return new ResponseEntity<>(vehiculeDtoMapper.toResponse(vehiculeService.findByPlate(plate)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<VehiculeDtoResponse>> findAllByBrand(String brand) {
        return new ResponseEntity<>(vehiculeDtoMapper.toResponses(vehiculeService.findByBrand(brand)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        vehiculeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<VehiculeDtoResponse> update(UUID id, VehiculeDtoRequest vehiculeDtoRequest) {
        return new ResponseEntity<>(
                vehiculeDtoMapper.toResponse(vehiculeService.update(id, vehiculeDtoMapper.toDomain(vehiculeDtoRequest))),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<VehiculeDtoResponse>> findAll() {
        return new ResponseEntity<>(vehiculeDtoMapper.toResponses(vehiculeService.findAll()), HttpStatus.OK);
    }

}
