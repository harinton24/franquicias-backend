package com.accenture.franquicias.application.mappers;

import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.infrastructure.dto.ProductoDTO;
import com.accenture.franquicias.infrastructure.dto.ProductoMasStockDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDTO toDTO(Producto producto);
    Producto toEntity(ProductoDTO dto);
    ProductoMasStockDTO toProductoMasStockDTO(Producto producto);
}