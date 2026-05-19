package com.accenture.franquicias.infrastructure.adapters.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateOrUpdateBaseDTO {
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;
}