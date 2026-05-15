package com.accenture.franquicias.infrastructure.adapters.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoMasStockDTO {
    private UUID id;
    private String nombre;
    private Integer stock;
    private UUID sucursalId;
}