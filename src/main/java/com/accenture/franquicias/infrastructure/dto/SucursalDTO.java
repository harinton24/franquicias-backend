package com.accenture.franquicias.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
    private UUID id;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío")
    private String nombre;

    private UUID franquiciaId;

    private List<ProductoDTO> productos;
}