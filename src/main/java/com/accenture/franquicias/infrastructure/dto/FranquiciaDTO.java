package com.accenture.franquicias.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranquiciaDTO {
    private UUID id;

    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    private String nombre;
}