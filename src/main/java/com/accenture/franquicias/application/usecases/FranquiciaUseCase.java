package com.accenture.franquicias.application.usecases;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.domain.ports.FranquiciaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class FranquiciaUseCase {

    private final FranquiciaRepositoryPort repositoryPort;

    public Mono<Franquicia> crearFranquicia(Franquicia franquicia) {
        franquicia.setId(UUID.randomUUID());
        franquicia.setNew(true);
        return repositoryPort.saveFranquicia(franquicia);
    }

    public Mono<Sucursal> agregarSucursal(Sucursal sucursal) {
        sucursal.setId(UUID.randomUUID());
        sucursal.setNew(true);
        return repositoryPort.saveSucursal(sucursal);
    }

    public Mono<Producto> agregarProducto(Producto producto) {
        producto.setId(UUID.randomUUID());
        producto.setNew(true);
        return repositoryPort.saveProducto(producto);
    }

    public Mono<Void> eliminarProducto(UUID productoId) {
        return repositoryPort.deleteProducto(productoId);
    }

    public Mono<Producto> modificarStock(UUID productoId, Integer nuevoStock) {
        if (nuevoStock < 0) {
            return Mono.error(new IllegalArgumentException("El nuevo stock no puede ser negativo"));
        }
        return repositoryPort.updateStock(productoId, nuevoStock);
    }

    public Flux<Producto> obtenerProductosMasStock(UUID franquiciaId) {
        return repositoryPort.findTopStockProductsByFranquicia(franquiciaId);
    }

    public Mono<Franquicia> renombrarFranquicia(UUID id, String nombre) {
        return repositoryPort.updateNombreFranquicia(id, nombre);
    }

    public Mono<Sucursal> renombrarSucursal(UUID id, String nombre) {
        return repositoryPort.updateNombreSucursal(id, nombre);
    }

    public Mono<Producto> renombrarProducto(UUID id, String nombre) {
        return repositoryPort.updateNombreProducto(id, nombre);
    }
}