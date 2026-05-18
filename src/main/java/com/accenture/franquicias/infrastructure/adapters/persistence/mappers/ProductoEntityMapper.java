package com.accenture.franquicias.infrastructure.adapters.persistence.mappers;

import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.infrastructure.adapters.persistence.entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoEntityMapper {
    Producto toDomain(ProductoEntity entity);

    @Mapping(target = "newEntry", ignore = true)
    ProductoEntity toEntity(Producto domain);
}