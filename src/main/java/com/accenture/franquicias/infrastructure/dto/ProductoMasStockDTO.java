package com.accenture.franquicias.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoMasStockDTO {
    private String nombreSucursal;
    private String nombreProducto;
    private Integer stock;
}