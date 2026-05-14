package com.accenture.franquicias.domain.ports;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FranquiciaRepositoryPort {

    // Franquicia
    Mono<Franquicia> saveFranquicia(Franquicia franquicia);
    Mono<Franquicia> updateNombreFranquicia(UUID id, String nuevoNombre);

    // Sucursal
    Mono<Sucursal> saveSucursal(Sucursal sucursal);
    Mono<Sucursal> updateNombreSucursal(UUID id, String nuevoNombre);

    // Producto
    Mono<Producto> saveProducto(Producto producto);
    Mono<Void> deleteProducto(UUID id);
    Mono<Producto> updateStock(UUID productoId, Integer nuevoStock);
    Mono<Producto> updateNombreProducto(UUID productoId, String nuevoNombre);

    Flux<Producto> findTopStockProductsByFranquicia(UUID franquiciaId);
}