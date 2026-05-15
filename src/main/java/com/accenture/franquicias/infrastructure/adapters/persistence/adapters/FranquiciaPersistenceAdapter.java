package com.accenture.franquicias.infrastructure.adapters.persistence.adapters;

import com.accenture.franquicias.domain.models.Franquicia;
import com.accenture.franquicias.domain.models.Producto;
import com.accenture.franquicias.domain.models.Sucursal;
import com.accenture.franquicias.domain.ports.FranquiciaRepositoryPort;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.FranquiciaRepository;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.ProductoRepository;
import com.accenture.franquicias.infrastructure.adapters.persistence.repositories.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FranquiciaPersistenceAdapter implements FranquiciaRepositoryPort {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    @Override
    public Mono<Franquicia> saveFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public Mono<Franquicia> updateNombreFranquicia(UUID id, String nuevoNombre) {
        return franquiciaRepository.findById(id)
                .flatMap(f -> {
                    f.setNombre(nuevoNombre);
                    return franquiciaRepository.save(f);
                });
    }

    @Override
    public Mono<Sucursal> saveSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Mono<Sucursal> updateNombreSucursal(UUID id, String nuevoNombre) {
        return sucursalRepository.findById(id)
                .flatMap(s -> {
                    s.setNombre(nuevoNombre);
                    return sucursalRepository.save(s);
                });
    }

    @Override
    public Mono<Producto> saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Void> deleteProducto(UUID id) {
        return productoRepository.deleteById(id);
    }

    @Override
    public Mono<Producto> updateStock(UUID productoId, Integer nuevoStock) {
        return productoRepository.findById(productoId)
                .flatMap(p -> {
                    p.setStock(nuevoStock);
                    return productoRepository.save(p);
                });
    }

    @Override
    public Mono<Producto> updateNombreProducto(UUID productoId, String nuevoNombre) {
        return productoRepository.findById(productoId)
                .flatMap(p -> {
                    p.setNombre(nuevoNombre);
                    return productoRepository.save(p);
                });
    }

    @Override
    public Flux<Producto> findTopStockProductsByFranquicia(UUID franquiciaId) {
        return productoRepository.findTopStockProductsByFranquicia(franquiciaId);
    }
}