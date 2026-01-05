package com.atomic.apiuser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.atomic.apiuser.domain.Vehicule;
import com.atomic.apiuser.dto.VehiculeDtoRequest;
import com.atomic.apiuser.dto.VehiculeDtoResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehiculeDtoMapper {

    Vehicule toDomain(VehiculeDtoRequest vehiculeDtoRequest);

    VehiculeDtoResponse toResponse(Vehicule vehicule);

    List<VehiculeDtoResponse> toResponses(List<Vehicule> vehiculeList);
}
