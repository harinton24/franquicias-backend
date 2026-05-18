package com.accenture.franquicias.infrastructure.adapters.web.mappers;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.infrastructure.adapters.web.dto.FranquiciaCreateDTO;
import com.accenture.franquicias.infrastructure.adapters.web.dto.FranquiciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FranquiciaMapper {
    FranquiciaDTO toDTO(Franquicia franquicia);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sucursales", ignore = true)
    Franquicia toEntity(FranquiciaCreateDTO dto);

    @Mapping(target = "sucursales", ignore = true)
    Franquicia toEntity(FranquiciaDTO dto);
}