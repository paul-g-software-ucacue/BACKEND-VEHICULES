package com.atomic.apiuser.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atomic.apiuser.dto.VehiculeDtoRequest;
import com.atomic.apiuser.dto.VehiculeDtoResponse;

@RequestMapping("/v1/vehicules")
public interface VehiculeController {
    @PostMapping
    ResponseEntity<VehiculeDtoResponse> save(@RequestBody VehiculeDtoRequest vehiculeDtoRequest);

    @GetMapping
    ResponseEntity<List<VehiculeDtoResponse>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<VehiculeDtoResponse> findById(@PathVariable UUID id);

    @GetMapping(params = "plate")
    ResponseEntity<VehiculeDtoResponse> findByPlate(@RequestParam String plate);

    @GetMapping(params = "brand")
    ResponseEntity<List<VehiculeDtoResponse>> findAllByBrand(@RequestParam String brand);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<VehiculeDtoResponse> update(@PathVariable UUID id, @RequestBody VehiculeDtoRequest vehiculeDtoRequest);
}
