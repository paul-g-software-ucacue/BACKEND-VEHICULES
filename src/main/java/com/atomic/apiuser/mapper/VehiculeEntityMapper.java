package com.atomic.apiuser.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.atomic.apiuser.domain.Vehicule;
import com.atomic.apiuser.entity.VehiculeEntity;

@Mapper(componentModel = "spring")
public interface VehiculeEntityMapper {

    Vehicule toDomain(VehiculeEntity vehiculeEntity);

    List<Vehicule> toDomains(List<VehiculeEntity> vehiculeEntityList);

    VehiculeEntity toEntity(Vehicule vehicule);

    List<VehiculeEntity> toEntities(List<Vehicule> vehiculeList);
}
