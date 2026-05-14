package com.accenture.franquicias.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private UUID id;
    private String nombre;
    private Integer stock;
}