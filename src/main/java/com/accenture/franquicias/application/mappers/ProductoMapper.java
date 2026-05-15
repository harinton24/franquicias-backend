package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.infrastructure.adapters.web.dto.ProductoCreateDTO;
import com.accenture.franquicias.infrastructure.adapters.web.dto.ProductoDTO;
import com.accenture.franquicias.infrastructure.adapters.web.dto.ProductoMasStockDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDTO toDTO(Producto producto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "new", ignore = true)
    @Mapping(target = "sucursalId", ignore = true)
    Producto toEntity(ProductoCreateDTO dto);

    @Mapping(target = "new", ignore = true)
    Producto toEntity(ProductoDTO dto);

    ProductoMasStockDTO toProductoMasStockDTO(Producto producto);
}