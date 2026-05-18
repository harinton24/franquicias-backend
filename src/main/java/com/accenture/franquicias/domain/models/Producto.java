package com.accenture.franquicias.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private UUID id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @NotNull(message = "El stock es obligatorio")
    private Integer stock;

    private UUID sucursalId;
}