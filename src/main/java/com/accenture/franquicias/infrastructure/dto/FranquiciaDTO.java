package com.accenture.franquicias.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranquiciaDTO {
    private UUID id;
    private String nombre;
}