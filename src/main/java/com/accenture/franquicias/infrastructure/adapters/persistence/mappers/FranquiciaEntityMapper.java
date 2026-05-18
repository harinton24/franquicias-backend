package com.accenture.franquicias.infrastructure.adapters.persistence.mappers;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.FranquiciaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FranquiciaEntityMapper {
    @Mapping(target = "sucursales", ignore = true)
    Franquicia toDomain(FranquiciaEntity entity);

    @Mapping(target = "newEntry", ignore = true)
    FranquiciaEntity toEntity(Franquicia domain);
}