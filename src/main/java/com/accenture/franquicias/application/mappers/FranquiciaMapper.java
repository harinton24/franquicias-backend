package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.infrastructure.adapters.web.dto.FranquiciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FranquiciaMapper {
    FranquiciaDTO toDTO(Franquicia franquicia);

    @Mapping(target = "sucursales", ignore = true)
    @Mapping(target = "new", ignore = true)
    Franquicia toEntity(FranquiciaDTO dto);
}