package com.accenture.franquicias.infrastructure.adapters.web.mappers;

import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.infrastructure.adapters.web.dto.SucursalCreateDTO;
import com.accenture.franquicias.infrastructure.adapters.web.dto.SucursalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SucursalMapper {
    SucursalDTO toDTO(Sucursal sucursal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "franquiciaId", source = "franquiciaId")
    Sucursal toEntity(SucursalCreateDTO dto);

    Sucursal toEntity(SucursalDTO dto);
}