package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.infrastructure.dto.FranquiciaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranquiciaMapper {
    FranquiciaDTO toDTO(Franquicia franquicia);
    Franquicia toEntity(FranquiciaDTO dto);
}