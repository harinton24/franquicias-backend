package com.accenture.franquicias.infrastructure.adapters.persistence.mappers;

import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.SucursalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SucursalEntityMapper {
    @Mapping(target = "productos", ignore = true)
    Sucursal toDomain(SucursalEntity entity);

    @Mapping(target = "newEntry", ignore = true)
    SucursalEntity toEntity(Sucursal domain);
}