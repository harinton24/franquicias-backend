package com.accenture.franquicias.infrastructure.adapters.web.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductoCreateDTO extends ProductoCreateOrUpdateBaseDTO {
    @Min(value = 0, message = "El stock inicial no puede ser negativo")
    private int stock;
}