package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.infrastructure.dto.SucursalDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SucursalMapper {
    SucursalDTO toDTO(Sucursal sucursal);
    Sucursal toEntity(SucursalDTO dto);
}