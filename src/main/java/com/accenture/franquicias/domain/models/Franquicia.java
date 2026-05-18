package com.accenture.franquicias.domain.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Franquicia {

    private UUID id;

    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    private String nombre;

    private List<Sucursal> sucursales;
}