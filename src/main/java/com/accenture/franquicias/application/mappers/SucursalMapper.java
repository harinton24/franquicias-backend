package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.infrastructure.adapters.web.dto.SucursalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SucursalMapper {
    SucursalDTO toDTO(Sucursal sucursal);

    @Mapping(target = "new", ignore = true)
    Sucursal toEntity(SucursalDTO dto);
}